package com.fittrackpro.repository;

import com.fittrackpro.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByExerciseNameContainingIgnoreCase(String exerciseName);
}