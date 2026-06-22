package com.dmggg.train_app.dtos.workout;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WorkoutExerciseRequest {

  @Min(value = 1, message = "Min Reps must be at least 1")
  private int minReps;

  @Min(value = 2, message = "Max Reps must be at least 2")
  private int maxReps;

  @NotNull(message = "Exercise is required")
  private Long exercise;
  
  @NotNull(message = "Workout is required")
  private Long workout;

  public WorkoutExerciseRequest() {
  }

  public WorkoutExerciseRequest(int minReps, int maxReps, Long exercise, Long workout) {
    this.minReps = minReps;
    this.maxReps = maxReps;
    this.exercise = exercise;
    this.workout = workout;
  }

  public int getMinReps() {
    return minReps;
  }

  public void setMinReps(int minReps) {
    this.minReps = minReps;
  }

  public int getMaxReps() {
    return maxReps;
  }

  public void setMaxReps(int maxReps) {
    this.maxReps = maxReps;
  }

  public Long getExercise() {
    return exercise;
  }

  public void setExercise(Long exercise) {
    this.exercise = exercise;
  }

  public Long getWorkout() {
    return workout;
  }

  public void setWorkout(Long workout) {
    this.workout = workout;
  }

}
