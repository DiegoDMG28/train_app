package com.dmggg.train_app.services.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmggg.train_app.dtos.exercise.ExerciseRequest;
import com.dmggg.train_app.dtos.exercise.ExerciseResponse;
import com.dmggg.train_app.entities.agroupament.Agroupament;
import com.dmggg.train_app.entities.agroupament.SubAgroupament;
import com.dmggg.train_app.entities.exercise.Exercise;
import com.dmggg.train_app.entities.workout.WorkoutExercise;
import com.dmggg.train_app.repositories.agroupament.AgroupamentRepository;
import com.dmggg.train_app.repositories.agroupament.SubAgroupamentRepository;
import com.dmggg.train_app.repositories.exercise.ExerciseRepository;
import com.dmggg.train_app.repositories.workout.WorkoutExerciseRepository;
import com.dmggg.train_app.services.exceptions.DatabaseException;
import com.dmggg.train_app.services.exceptions.EntityNotFound;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExerciseService {

  private final ExerciseRepository repository;
  private final WorkoutExerciseRepository workoutExerciseRepository;
  private final AgroupamentRepository agroupamentRepository;
  private final SubAgroupamentRepository subAgroupamentRepository;

  @Transactional(readOnly = true)
  public List<ExerciseResponse> searchAll() {
    List<Exercise> list = repository.findAll();
    List<ExerciseResponse> listResponse = new ArrayList<>();
    list.forEach(x -> listResponse.add(new ExerciseResponse(x)));
    return listResponse;
  }

  @Transactional(readOnly = true)
  public ExerciseResponse searchById(Long id) {
    Optional<Exercise> exercise = repository.findById(id);
    return new ExerciseResponse(exercise.orElseThrow(() -> new EntityNotFound("Exercise not found on our database")));
  }

  @Transactional
  public ExerciseResponse insert(ExerciseRequest exerciseRequest) {
    Exercise exercise = new Exercise();

    exercise.setName(exerciseRequest.getName());

    if (exerciseRequest.getWorkoutExercises() != null && !exerciseRequest.getWorkoutExercises().isEmpty()) {
      exerciseRequest.getWorkoutExercises().forEach(id -> {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFound("WorkoutExercise id :" + id + " not found on our database"));
        workoutExercise.setExercise(exercise);
        workoutExerciseRepository.save(workoutExercise);
      });
    }

    if (exerciseRequest.getAgroupaments() != null && !exerciseRequest.getAgroupaments().isEmpty()) {
      exerciseRequest.getAgroupaments().forEach(id -> {
        Agroupament agroupament = agroupamentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFound("Agroupament id :" + id + " not found on our database"));
        exercise.getListAgroupaments().add(agroupament);
      });
    }

    if (exerciseRequest.getSubAgroupaments() != null && !exerciseRequest.getSubAgroupaments().isEmpty()) {
      exerciseRequest.getSubAgroupaments().forEach(id -> {
        SubAgroupament subAgroupament = subAgroupamentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFound("SubAgroupament id :" + id + " not found on our database"));
        exercise.getListSubAgroupaments().add(subAgroupament);
      });
    }

    Exercise saved = repository.save(exercise);
    return new ExerciseResponse(saved);
  }

  @Transactional
  public ExerciseResponse update(Long id, ExerciseRequest exerciseRequest) {
    Exercise exercise = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Exercise not found on our database"));

    exercise.setName(exerciseRequest.getName());

    if (exerciseRequest.getWorkoutExercises() != null) {
      new ArrayList<>(exercise.getListWorkoutExercises()).forEach(x -> {
        if (!exerciseRequest.getWorkoutExercises().contains(x.getId())) {
          x.setExercise(null);
          workoutExerciseRepository.save(x);
        }
      });

      exerciseRequest.getWorkoutExercises().forEach(x -> {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(x)
            .orElseThrow(() -> new EntityNotFound("WorkoutExercise id :" + x + " not found on our database"));
        if (!exercise.getListWorkoutExercises().contains(workoutExercise)) {
          workoutExercise.setExercise(exercise);
          workoutExerciseRepository.save(workoutExercise);
        }
      });
    }

    if (exerciseRequest.getAgroupaments() != null) {
      new ArrayList<>(exercise.getListAgroupaments()).forEach(x -> {
        if (!exerciseRequest.getAgroupaments().contains(x.getId())) {
          exercise.getListAgroupaments().remove(x);
        }
      });

      exerciseRequest.getAgroupaments().forEach(x -> {
        Agroupament agroupament = agroupamentRepository.findById(x)
            .orElseThrow(() -> new EntityNotFound("Agroupament id :" + x + " not found on our database"));
        if (!exercise.getListAgroupaments().contains(agroupament)) {
          exercise.getListAgroupaments().add(agroupament);
        }
      });
    }

    if (exerciseRequest.getSubAgroupaments() != null) {
      new ArrayList<>(exercise.getListSubAgroupaments()).forEach(x -> {
        if (!exerciseRequest.getSubAgroupaments().contains(x.getId())) {
          exercise.getListSubAgroupaments().remove(x);
        }
      });

      exerciseRequest.getSubAgroupaments().forEach(x -> {
        SubAgroupament subAgroupament = subAgroupamentRepository.findById(x)
            .orElseThrow(() -> new EntityNotFound("SubAgroupament id :" + x + " not found on our database"));
        if (!exercise.getListSubAgroupaments().contains(subAgroupament)) {
          exercise.getListSubAgroupaments().add(subAgroupament);
        }
      });
    }

    Exercise updated = repository.save(exercise);
    return new ExerciseResponse(updated);
  }

  @Transactional
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new EntityNotFound("Exercise not found on our database");
    }

    try {

      Exercise exercise = repository.findById(id)
          .orElseThrow(() -> new EntityNotFound("Exercise not found on our database"));

      new ArrayList<>(exercise.getListWorkoutExercises()).forEach(x -> {
        x.setExercise(null);
        workoutExerciseRepository.save(x);
      });

      exercise.getListAgroupaments().clear();
      exercise.getListSubAgroupaments().clear();

      repository.delete(exercise);

    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Integrity violation");
    }

  }
}