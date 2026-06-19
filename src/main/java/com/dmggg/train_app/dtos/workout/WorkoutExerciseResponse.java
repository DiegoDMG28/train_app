package com.dmggg.train_app.dtos.workout;

import com.dmggg.train_app.dtos.exercise.ExerciseSummaryResponse;
import com.dmggg.train_app.entities.workout.WorkoutExercise;

public class WorkoutExerciseResponse {
  private long id;
  private int minReps;
  private int maxReps;
  private WorkoutSummaryResponse workout;
  private ExerciseSummaryResponse exercise;

  public WorkoutExerciseResponse() {
  }

  public WorkoutExerciseResponse(long id, int minReps, int maxReps, WorkoutSummaryResponse workout,
      ExerciseSummaryResponse exercise) {
    this.id = id;
    this.minReps = minReps;
    this.maxReps = maxReps;
    this.workout = workout;
    this.exercise = exercise;
  }

  public WorkoutExerciseResponse(WorkoutExercise entity) {
    this.id = entity.getId();
    this.minReps = entity.getMinReps();
    this.maxReps = entity.getMaxReps();

    if (entity.getExercise() != null) {
      this.exercise = new ExerciseSummaryResponse(entity.getExercise());
    }
    if (entity.getWorkout() != null) {
      this.workout = new WorkoutSummaryResponse(entity.getWorkout());
    }
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public WorkoutSummaryResponse getWorkout() {
    return workout;
  }

  public void setWorkout(WorkoutSummaryResponse workout) {
    this.workout = workout;
  }

  public ExerciseSummaryResponse getExercise() {
    return exercise;
  }

  public void setExercise(ExerciseSummaryResponse exercise) {
    this.exercise = exercise;
  }

}
