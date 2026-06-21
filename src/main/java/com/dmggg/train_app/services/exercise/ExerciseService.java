package com.dmggg.train_app.services.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.dmggg.train_app.services.exceptions.EntityNotFound;

@Service
public class ExerciseService {

  @Autowired
  private ExerciseRepository repository;

  @Autowired
  private WorkoutExerciseRepository workoutExerciseRepository;

  @Autowired
  private AgroupamentRepository agroupamentRepository;
  
  @Autowired
  private SubAgroupamentRepository subAgroupamentRepository;

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
    return new ExerciseResponse(exercise.orElseThrow(()
        -> new EntityNotFound("Exercise not found on our database")));
  }

  @Transactional
  public ExerciseResponse insert(ExerciseRequest exerciseRequest) {
    Exercise exercise = new Exercise();

    exercise.setName(exerciseRequest.getName());

    if (exerciseRequest.getWorkoutExercises() != null && !exerciseRequest.getWorkoutExercises().isEmpty()) {
      List<WorkoutExercise> listWorkoutExercises = new ArrayList<>();
      exerciseRequest.getWorkoutExercises().forEach(id ->
        listWorkoutExercises.add(workoutExerciseRepository.findById(id)
          .orElseThrow(() -> new EntityNotFound("WorkoutExercise id :" + id + " not found on our database"))));

      exercise.setListWorkoutExercises(listWorkoutExercises);
    }

    if (exerciseRequest.getAgroupaments() != null && !exerciseRequest.getAgroupaments().isEmpty()) {
      List<Agroupament> listAgroupaments = new ArrayList<>();
      exerciseRequest.getAgroupaments().forEach(id ->
        listAgroupaments.add(agroupamentRepository.findById(id)
          .orElseThrow(() -> new EntityNotFound("Agroupament id :" + id + " not found on our database"))));
      exercise.setListAgroupaments(listAgroupaments);
    }

    if (exerciseRequest.getSubAgroupaments() != null && !exerciseRequest.getSubAgroupaments().isEmpty()) {
      List<SubAgroupament> listSubAgroupaments = new ArrayList<>();
      exerciseRequest.getSubAgroupaments().forEach(id ->
        listSubAgroupaments.add(subAgroupamentRepository.findById(id)
          .orElseThrow(() -> new EntityNotFound("SubAgroupament id :" + id + " not found on our database"))));
      exercise.setListSubAgroupaments(listSubAgroupaments);
    }

    exercise = repository.save(exercise);
    return new ExerciseResponse(exercise);
  }

  @Transactional
  public ExerciseResponse update(Long id, ExerciseRequest exerciseRequest) {
    Exercise exercise = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Exercise not found on our database"));
        
    exercise.setName(exerciseRequest.getName());

    if (exerciseRequest.getWorkoutExercises() != null) {
      List<WorkoutExercise> listWorkoutExercises = new ArrayList<>();
      exerciseRequest.getWorkoutExercises().forEach(x ->
        listWorkoutExercises.add(workoutExerciseRepository.findById(x)
          .orElseThrow(() -> new EntityNotFound("WorkoutExercise id :" + x + " not found on our database"))));

      exercise.getListWorkoutExercises().clear();
      exercise.getListWorkoutExercises().addAll(listWorkoutExercises);
      exercise.setListWorkoutExercises(listWorkoutExercises);
    }

    if (exerciseRequest.getAgroupaments() != null) {
      List<Agroupament> listAgroupaments = new ArrayList<>();
      exerciseRequest.getAgroupaments().forEach(x ->
        listAgroupaments.add(agroupamentRepository.findById(x)
          .orElseThrow(() -> new EntityNotFound("Agroupament id :" + x + " not found on our database"))));

      exercise.getListAgroupaments().clear();
      exercise.getListAgroupaments().addAll(listAgroupaments);
      exercise.setListAgroupaments(listAgroupaments);
    }

    if (exerciseRequest.getSubAgroupaments() != null) {
      List<SubAgroupament> listSubAgroupaments = new ArrayList<>();
      exerciseRequest.getSubAgroupaments().forEach(x ->
        listSubAgroupaments.add(subAgroupamentRepository.findById(x)
          .orElseThrow(() -> new EntityNotFound("SubAgroupament id :" + x + " not found on our database"))));

      exercise.getListSubAgroupaments().clear();
      exercise.getListSubAgroupaments().addAll(listSubAgroupaments);
      exercise.setListSubAgroupaments(listSubAgroupaments);
    }

    exercise = repository.save(exercise);
    return new ExerciseResponse(exercise);
  }

  @Transactional
  public void delete(Long id) {
    Exercise exercise = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Exercise not found on our database"));
    repository.delete(exercise);
  }
}
