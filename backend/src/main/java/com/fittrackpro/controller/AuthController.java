package com.fittrackpro.controller;

import com.fittrackpro.config.JwtUtil;
import com.fittrackpro.dto.*;
import com.fittrackpro.entity.User;
import com.fittrackpro.repository.UserRepository;
import com.fittrackpro.service.UserService;
import com.fittrackpro.dto.UserRequestDTO;
import com.fittrackpro.dto.UserResponseDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthController(UserRepository userRepository,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder,
                          UserService userService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail(),user.getRole());

        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public UserResponseDTO register(
            @RequestBody AuthRegisterRequest request
    ) {

        UserRequestDTO dto = new UserRequestDTO();

        dto.setName(request.getName());
        dto.setEmail(request.getEmail());
        dto.setPassword(request.getPassword());
        dto.setRole("USER");

        return userService.createUser(dto);
    }
}