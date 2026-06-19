package com.dmggg.train_app.dtos.exercise;

import com.dmggg.train_app.entities.exercise.Exercise;

public class ExerciseSummaryResponse {
  private long id;
  private String name;

  public ExerciseSummaryResponse() {}

  public ExerciseSummaryResponse(Exercise entity) {
    this.id = entity.getId();
    this.name = entity.getName();
  }

  public long getId() { return id; }
  public void setId(long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
