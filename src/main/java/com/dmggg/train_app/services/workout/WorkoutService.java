package com.dmggg.train_app.services.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmggg.train_app.dtos.workout.WorkoutRequest;
import com.dmggg.train_app.dtos.workout.WorkoutResponse;
import com.dmggg.train_app.entities.workout.Workout;
import com.dmggg.train_app.entities.workout.WorkoutExercise;
import com.dmggg.train_app.repositories.workout.WorkoutExerciseRepository;
import com.dmggg.train_app.repositories.workout.WorkoutRepository;
import com.dmggg.train_app.services.exceptions.DatabaseException;
import com.dmggg.train_app.services.exceptions.EntityNotFound;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorkoutService {

  private final WorkoutRepository repository;
  private final WorkoutExerciseRepository workoutExerciseRepository;

  @Transactional(readOnly = true)
  public List<WorkoutResponse> searchAll() {
    List<Workout> list = repository.findAll();
    List<WorkoutResponse> listResponse = new ArrayList<>();
    list.forEach(x -> listResponse.add(new WorkoutResponse(x)));
    return listResponse;
  }

  @Transactional(readOnly = true)
  public WorkoutResponse searchById(Long id) {
    Optional<Workout> workout = repository.findById(id);
    return new WorkoutResponse(workout.orElseThrow(() -> new EntityNotFound("Workout not found on our database")));
  }

  @Transactional
  public WorkoutResponse insert(WorkoutRequest workoutRequest) {
    Workout workout = new Workout();

    workout.setName(workoutRequest.getName());

    if (workoutRequest.getWorkoutExercises() != null && !workoutRequest.getWorkoutExercises().isEmpty()) {
      Workout saved = repository.save(workout);

      workoutRequest.getWorkoutExercises().forEach(id -> {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFound("WorkoutExercise id :" + id + " not found on our database"));
        workoutExercise.setWorkout(saved);
        workoutExerciseRepository.save(workoutExercise);
      });

      return new WorkoutResponse(saved);
    }

    Workout saved = repository.save(workout);
    return new WorkoutResponse(saved);
  }

  @Transactional
  public WorkoutResponse update(Long id, WorkoutRequest workoutRequest) {
    Workout workout = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Workout not found on our database"));

    workout.setName(workoutRequest.getName());

    if (workoutRequest.getWorkoutExercises() != null) {
      new ArrayList<>(workout.getListWorkoutExercises()).forEach(x -> {
        if (!workoutRequest.getWorkoutExercises().contains(x.getId())) {
          x.setWorkout(null);
          workoutExerciseRepository.save(x);
        }
      });

      workoutRequest.getWorkoutExercises().forEach(x -> {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(x)
            .orElseThrow(() -> new EntityNotFound("WorkoutExercise id :" + x + " not found on our database"));
        if (!workout.getListWorkoutExercises().contains(workoutExercise)) {
          workoutExercise.setWorkout(workout);
          workoutExerciseRepository.save(workoutExercise);
        }
      });
    }

    Workout updated = repository.save(workout);
    return new WorkoutResponse(updated);
  }

  @Transactional
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new EntityNotFound("Workout not found on our database");
    }
    try {
      Workout workout = repository.findById(id)
          .orElseThrow(() -> new EntityNotFound("Workout not found on our database"));

      new ArrayList<>(workout.getListWorkoutExercises()).forEach(x -> {
        x.setWorkout(null);
        workoutExerciseRepository.save(x);
      });

      repository.delete(workout);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Integrity violation");
    }

  }
}