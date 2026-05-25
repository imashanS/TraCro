package com.fittrackpro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User request payload")
public class UserRequestDTO {

    @Schema(example = "Imashan")
    private String name;

    @Schema(example = "imashan@gmail.com")
    private String email;

    @Schema(example = "password123")
    private String password;

    @Schema(example = "USER")
    private String role;
}