package com.dmggg.train_app.dtos.workout;

import java.time.Instant;

import com.dmggg.train_app.entities.workout.Workout;

public class WorkoutSummaryResponse {
  private long id;
  private String name;
  private Instant instant;

  public WorkoutSummaryResponse() {
  }

  public WorkoutSummaryResponse(Workout entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.instant = entity.getInstant();
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

}
