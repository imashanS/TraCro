package com.fittrackpro.controller;

import com.fittrackpro.dto.WorkoutRequestDTO;
import com.fittrackpro.dto.WorkoutResponseDTO;
import com.fittrackpro.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public Page<WorkoutResponseDTO> getAllWorkouts(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "workoutDate") String sortBy

    ) {

        return workoutService.getAllWorkouts(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public WorkoutResponseDTO getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

    @PutMapping("/{id}")
    public WorkoutResponseDTO updateWorkout(
            @PathVariable Long id,
            @Valid @RequestBody WorkoutRequestDTO dto
    ) {
        return workoutService.updateWorkout(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
    }

    @GetMapping("/search")
    public List<WorkoutResponseDTO> searchWorkouts(
            @RequestParam String exercise
    ) {
        return workoutService.searchWorkouts(exercise);
    }

    @GetMapping("/user/{userId}")
    public List<WorkoutResponseDTO> getWorkoutsByUser(
            @PathVariable Long userId
    ) {
        return workoutService.getWorkoutsByUser(userId);
    }

    @PostMapping
    public WorkoutResponseDTO createWorkout(
            @Valid @RequestBody WorkoutRequestDTO dto,
            Authentication authentication
    ) {
        String email = authentication.getName(); // extracted from JWT
        return workoutService.createWorkout(dto, email);
    }

    @GetMapping("/my")
    public List<WorkoutResponseDTO> getMyWorkouts(
            Authentication authentication
    ) {

        String email = authentication.getName();

        return workoutService.getWorkoutsByUserEmail(email);
    }

    @GetMapping("/analytics")
    public Map<String, Object> getWorkoutAnalytics(
            Authentication authentication
    ) {

        String email = authentication.getName();

        return workoutService.getWorkoutAnalytics(email);
    }
}