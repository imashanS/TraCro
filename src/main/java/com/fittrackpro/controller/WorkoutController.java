package com.fittrackpro.controller;

import com.fittrackpro.dto.WorkoutRequestDTO;
import com.fittrackpro.dto.WorkoutResponseDTO;
import com.fittrackpro.service.WorkoutService;
import jakarta.validation.Valid;
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
    public List<WorkoutResponseDTO> getAllWorkouts() {
        return workoutService.getAllWorkouts();
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