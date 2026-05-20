package com.fittrackpro.controller;

import com.fittrackpro.dto.WorkoutRequestDTO;
import com.fittrackpro.dto.WorkoutResponseDTO;
import com.fittrackpro.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public WorkoutResponseDTO createWorkout(
            @Valid @RequestBody WorkoutRequestDTO dto
    ) {
        return workoutService.createWorkout(dto);
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
}