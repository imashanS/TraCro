package com.fittrackpro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class WorkoutResponseDTO {

    private Long id;
    private String exerciseName;
    private int sets;
    private int reps;
    private double weight;
    private LocalDate workoutDate;
}