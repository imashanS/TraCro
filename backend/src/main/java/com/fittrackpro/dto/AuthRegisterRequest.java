package com.fittrackpro.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRegisterRequest {

    private String name;
    private String email;
    private String password;
    private String role;

    // getters and setters
}
