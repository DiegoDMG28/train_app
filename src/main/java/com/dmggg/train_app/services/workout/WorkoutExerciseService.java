package com.dmggg.train_app.services.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmggg.train_app.dtos.workout.WorkoutExerciseRequest;
import com.dmggg.train_app.dtos.workout.WorkoutExerciseResponse;
import com.dmggg.train_app.entities.workout.WorkoutExercise;
import com.dmggg.train_app.repositories.exercise.ExerciseRepository;
import com.dmggg.train_app.repositories.workout.WorkoutExerciseRepository;
import com.dmggg.train_app.repositories.workout.WorkoutRepository;
import com.dmggg.train_app.services.exceptions.EntityNotFound;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorkoutExerciseService {

  private final WorkoutExerciseRepository repository;
  private final ExerciseRepository exerciseRepository;
  private final WorkoutRepository workoutRepository;

  @Transactional(readOnly = true)
  public List<WorkoutExerciseResponse> searchAll() {
    List<WorkoutExercise> list = repository.findAll();
    List<WorkoutExerciseResponse> listResponse = new ArrayList<>();
    list.forEach(x -> listResponse.add(new WorkoutExerciseResponse(x)));
    return listResponse;
  }

  @Transactional(readOnly = true)
  public WorkoutExerciseResponse searchById(Long id) {
    Optional<WorkoutExercise> workoutExercise = repository.findById(id);
    return new WorkoutExerciseResponse(
        workoutExercise.orElseThrow(() -> new EntityNotFound("WorkoutExercise not found on our database")));
  }

  @Transactional
  public WorkoutExerciseResponse insert(WorkoutExerciseRequest workoutExerciseRequest) {
    WorkoutExercise workoutExercise = new WorkoutExercise();

    workoutExercise.setMinReps(workoutExerciseRequest.getMinReps());
    workoutExercise.setMaxReps(workoutExerciseRequest.getMaxReps());

    if (workoutExerciseRequest.getExercise() != null) {
      workoutExercise.setExercise(exerciseRepository.findById(workoutExerciseRequest.getExercise())
        .orElseThrow(() -> new EntityNotFound("Exercise not found on our database")));
    }

    if (workoutExerciseRequest.getWorkout() != null) {
      workoutExercise.setWorkout(workoutRepository.findById(workoutExerciseRequest.getWorkout())
        .orElseThrow(() -> new EntityNotFound("Workout not found on our database")));
    }

    WorkoutExercise saved = repository.save(workoutExercise);
    return new WorkoutExerciseResponse(saved);
  }

  @Transactional
  public WorkoutExerciseResponse update(Long id, WorkoutExerciseRequest workoutExerciseRequest) {
    WorkoutExercise workoutExercise = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("WorkoutExercise not found on our database"));

    workoutExercise.setMinReps(workoutExerciseRequest.getMinReps());
    workoutExercise.setMaxReps(workoutExerciseRequest.getMaxReps());

    if (workoutExerciseRequest.getExercise() != null) {
      workoutExercise.setExercise(exerciseRepository.findById(workoutExerciseRequest.getExercise())
        .orElseThrow(() -> new EntityNotFound("Exercise not found on our database")));
    }

    if (workoutExerciseRequest.getWorkout() != null) {
      workoutExercise.setWorkout(workoutRepository.findById(workoutExerciseRequest.getWorkout())
        .orElseThrow(() -> new EntityNotFound("Workout not found on our database")));
    }

    WorkoutExercise updated = repository.save(workoutExercise);
    return new WorkoutExerciseResponse(updated);
  }

  @Transactional
  public void delete(Long id) {
    WorkoutExercise workoutExercise = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("WorkoutExercise not found on our database"));
    repository.delete(workoutExercise);
  }
}