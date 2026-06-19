package com.dmggg.train_app.services.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmggg.train_app.dtos.exercise.ExerciseRequest;
import com.dmggg.train_app.dtos.exercise.ExerciseResponse;
import com.dmggg.train_app.entities.exercise.Exercise;
import com.dmggg.train_app.repositories.exercise.ExerciseRepository;
import com.dmggg.train_app.services.exceptions.EntityNotFound;

@Service
public class ExerciseService {

  @Autowired
  private ExerciseRepository repository;

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
    exercise = repository.save(exercise);
    return new ExerciseResponse(exercise);
  }

  @Transactional
  public ExerciseResponse update(Long id, ExerciseRequest exerciseRequest) {
    Exercise exercise = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Exercise not found on our database"));
    exercise.setName(exerciseRequest.getName());
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
