package com.dmggg.train_app.dtos.workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutExerciseRequest {
  private int minReps;
  private int maxReps;
  private List<Long> listLong = new ArrayList<>();

  public WorkoutExerciseRequest() {}

  public WorkoutExerciseRequest(int minReps, int maxReps, List<Long> listLong) {
    this.minReps = minReps;
    this.maxReps = maxReps;
    this.listLong = listLong;
  }

  public int getMinReps() { return minReps; }
  public void setMinReps(int minReps) { this.minReps = minReps; }

  public int getMaxReps() { return maxReps; }
  public void setMaxReps(int maxReps) { this.maxReps = maxReps; }

  public List<Long> getListLong() { return listLong; }
  public void setListLong(List<Long> listLong) { this.listLong = listLong; }
}
