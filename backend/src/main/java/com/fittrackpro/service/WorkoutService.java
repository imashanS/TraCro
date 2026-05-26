package com.fittrackpro.service;

import com.fittrackpro.dto.WorkoutRequestDTO;
import com.fittrackpro.dto.WorkoutResponseDTO;
import com.fittrackpro.entity.Workout;
import com.fittrackpro.repository.WorkoutRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.fittrackpro.repository.UserRepository;
import com.fittrackpro.entity.User;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    private final UserRepository userRepository;

    public WorkoutService(
            WorkoutRepository workoutRepository,
            UserRepository userRepository
    ) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    // GET ALL WORKOUTS
    public Page<WorkoutResponseDTO> getAllWorkouts(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).descending()
        );

        return workoutRepository.findAll(pageable)
                .map(workout -> new WorkoutResponseDTO(
                        workout.getId(),
                        workout.getExerciseName(),
                        workout.getSets(),
                        workout.getReps(),
                        workout.getWeight(),
                        workout.getWorkoutDate()
                ));
    }

    // GET WORKOUT BY ID
    public WorkoutResponseDTO getWorkoutById(Long id) {

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        return new WorkoutResponseDTO(
                workout.getId(),
                workout.getExerciseName(),
                workout.getSets(),
                workout.getReps(),
                workout.getWeight(),
                workout.getWorkoutDate()
        );
    }

    // UPDATE WORKOUT
    public WorkoutResponseDTO updateWorkout(Long id, WorkoutRequestDTO dto) {

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        workout.setExerciseName(dto.getExerciseName());
        workout.setSets(dto.getSets());
        workout.setReps(dto.getReps());
        workout.setWeight(dto.getWeight());
        workout.setWorkoutDate(dto.getWorkoutDate());

        Workout updatedWorkout = workoutRepository.save(workout);

        return new WorkoutResponseDTO(
                updatedWorkout.getId(),
                updatedWorkout.getExerciseName(),
                updatedWorkout.getSets(),
                updatedWorkout.getReps(),
                updatedWorkout.getWeight(),
                updatedWorkout.getWorkoutDate()
        );
    }

    // DELETE WORKOUT
    public void deleteWorkout(Long id) {

        workoutRepository.deleteById(id);
    }
    public List<WorkoutResponseDTO> searchWorkouts(String exerciseName) {

        return workoutRepository
                .findByExerciseNameContainingIgnoreCase(exerciseName)
                .stream()
                .map(workout -> new WorkoutResponseDTO(
                        workout.getId(),
                        workout.getExerciseName(),
                        workout.getSets(),
                        workout.getReps(),
                        workout.getWeight(),
                        workout.getWorkoutDate()
                ))
                .toList();
    }
    public List<WorkoutResponseDTO> getWorkoutsByUser(Long userId) {

        return workoutRepository.findByUserId(userId)
                .stream()
                .map(workout -> new WorkoutResponseDTO(
                        workout.getId(),
                        workout.getExerciseName(),
                        workout.getSets(),
                        workout.getReps(),
                        workout.getWeight(),
                        workout.getWorkoutDate()
                ))
                .toList();
    }

    public WorkoutResponseDTO createWorkout(WorkoutRequestDTO dto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Workout workout = new Workout();
        workout.setExerciseName(dto.getExerciseName());
        workout.setSets(dto.getSets());
        workout.setReps(dto.getReps());
        workout.setWeight(dto.getWeight());
        workout.setWorkoutDate(dto.getWorkoutDate());
        workout.setUser(user);

        Workout saved = workoutRepository.save(workout);
        return new WorkoutResponseDTO(
                saved.getId(),
                saved.getExerciseName(),
                saved.getSets(),
                saved.getReps(),
                saved.getWeight(),
                saved.getWorkoutDate()
        );
    }
}