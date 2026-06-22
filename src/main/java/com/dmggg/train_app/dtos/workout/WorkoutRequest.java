package com.dmggg.train_app.dtos.workout;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class WorkoutRequest {
  @NotBlank(message = "Name is required")
  private String name;

  private List<Long> workoutExercises = new ArrayList<>();

  public WorkoutRequest() {
  }

  public WorkoutRequest(String name, List<Long> workoutExercises) {
    this.name = name;
    this.workoutExercises = workoutExercises;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Long> getWorkoutExercises() {
    return workoutExercises;
  }

  public void setWorkoutExercises(List<Long> workoutExercises) {
    this.workoutExercises = workoutExercises;
  }

}
