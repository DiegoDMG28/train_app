package com.dmggg.train_app.dtos.workout;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.dmggg.train_app.entities.workout.Workout;

public class WorkoutResponse {
  private long id;
  private String name;
  private Instant instant;
  private List<WorkoutExerciseSummaryResponse> workoutExercises = new ArrayList<>();

  public WorkoutResponse() {
  }

  public WorkoutResponse(long id, String name, Instant instant, List<WorkoutExerciseSummaryResponse> workoutExercises) {
    this.id = id;
    this.name = name;
    this.instant = instant;
    this.workoutExercises = workoutExercises;
  }

  public WorkoutResponse(Workout entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.instant = entity.getInstant();

    if (entity.getListWorkoutExercises() != null) {
      entity.getListWorkoutExercises().forEach(x -> this.workoutExercises.add(new WorkoutExerciseSummaryResponse(x)));
    }
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Instant getInstant() {
    return instant;
  }

  public void setInstant(Instant instant) {
    this.instant = instant;
  }

  public List<WorkoutExerciseSummaryResponse> getWorkoutExercises() {
    return workoutExercises;
  }

  public void setWorkoutExercises(List<WorkoutExerciseSummaryResponse> workoutExercises) {
    this.workoutExercises = workoutExercises;
  }

}
