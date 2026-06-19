package com.dmggg.train_app.dtos.workout;

import com.dmggg.train_app.entities.workout.WorkoutExercise;

public class WorkoutExerciseSummaryResponse {
  private long id;
  private int minReps;
  private int maxReps;

  public WorkoutExerciseSummaryResponse() {}

  public WorkoutExerciseSummaryResponse(WorkoutExercise entity) {
    this.id = entity.getId();
    this.minReps = entity.getMinReps();
    this.maxReps = entity.getMaxReps();
  }

  public long getId() { return id; }
  public void setId(long id) { this.id = id; }

  public int getMinReps() { return minReps; }
  public void setMinReps(int minReps) { this.minReps = minReps; }

  public int getMaxReps() { return maxReps; }
  public void setMaxReps(int maxReps) { this.maxReps = maxReps; }
}
