package com.dmggg.train_app.dtos.workout;
public class WorkoutExerciseRequest {
  private int minReps;
  private int maxReps;
  private Long exercise;
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
