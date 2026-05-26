package com.fittrackpro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class WorkoutRequestDTO {

    @NotBlank(message = "Exercise name is required")
    private String exerciseName;

    @Min(1)
    private int sets;

    @Min(1)
    private int reps;

    @Positive
    private double weight;

    @NotNull(message = "Workout date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate workoutDate;

}