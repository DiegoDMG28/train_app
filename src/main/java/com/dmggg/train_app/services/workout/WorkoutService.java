package com.dmggg.train_app.services.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmggg.train_app.dtos.workout.WorkoutRequest;
import com.dmggg.train_app.dtos.workout.WorkoutResponse;
import com.dmggg.train_app.entities.workout.Workout;
import com.dmggg.train_app.repositories.workout.WorkoutRepository;
import com.dmggg.train_app.services.exceptions.EntityNotFound;

@Service
public class WorkoutService {

  @Autowired
  private WorkoutRepository repository;

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
    return new WorkoutResponse(workout.orElseThrow(()
        -> new EntityNotFound("Workout not found on our database")));
  }

  @Transactional
  public WorkoutResponse insert(WorkoutRequest workoutRequest) {
    Workout workout = new Workout();
    workout.setName(workoutRequest.getName());
    workout = repository.save(workout);
    return new WorkoutResponse(workout);
  }

  @Transactional
  public WorkoutResponse update(Long id, WorkoutRequest workoutRequest) {
    Workout workout = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Workout not found on our database"));
    workout.setName(workoutRequest.getName());
    workout = repository.save(workout);
    return new WorkoutResponse(workout);
  }

  @Transactional
  public void delete(Long id) {
    Workout workout = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Workout not found on our database"));
    repository.delete(workout);
  }
}
