package com.fittrackpro.service;

import com.fittrackpro.dto.WorkoutRequestDTO;
import com.fittrackpro.dto.WorkoutResponseDTO;
import com.fittrackpro.entity.Workout;
import com.fittrackpro.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // CREATE WORKOUT
    public WorkoutResponseDTO createWorkout(WorkoutRequestDTO dto) {

        Workout workout = new Workout();

        workout.setExerciseName(dto.getExerciseName());
        workout.setSets(dto.getSets());
        workout.setReps(dto.getReps());
        workout.setWeight(dto.getWeight());

        Workout savedWorkout = workoutRepository.save(workout);

        return new WorkoutResponseDTO(
                savedWorkout.getId(),
                savedWorkout.getExerciseName(),
                savedWorkout.getSets(),
                savedWorkout.getReps(),
                savedWorkout.getWeight()
        );
    }

    // GET ALL WORKOUTS
    public List<WorkoutResponseDTO> getAllWorkouts() {

        return workoutRepository.findAll()
                .stream()
                .map(workout -> new WorkoutResponseDTO(
                        workout.getId(),
                        workout.getExerciseName(),
                        workout.getSets(),
                        workout.getReps(),
                        workout.getWeight()
                ))
                .toList();
    }
}