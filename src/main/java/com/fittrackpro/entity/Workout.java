package com.fittrackpro.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name == "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String exerciseName;
    private int sets;
    private int reps;
    private double weight;

}
