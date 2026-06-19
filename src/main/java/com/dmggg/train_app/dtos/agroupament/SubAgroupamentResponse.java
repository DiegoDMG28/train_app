package com.dmggg.train_app.dtos.agroupament;

import java.util.ArrayList;
import java.util.List;

import com.dmggg.train_app.dtos.exercise.ExerciseSummaryResponse;
import com.dmggg.train_app.entities.agroupament.SubAgroupament;

public class SubAgroupamentResponse {
  private long id;
  private String name;
  private AgroupamentSummaryResponse agroupament;
  private List<ExerciseSummaryResponse> exercises = new ArrayList<>();

  public SubAgroupamentResponse() {
  }

  public SubAgroupamentResponse(long id, String name, AgroupamentSummaryResponse agroupament,
      List<ExerciseSummaryResponse> exercises) {
    this.id = id;
    this.name = name;
    this.agroupament = agroupament;
    this.exercises = exercises;
  }

  public SubAgroupamentResponse(SubAgroupament entity) {
    this.id = entity.getId();
    this.name = entity.getName();

    if (entity.getListExercises() != null) {
      entity.getListExercises().forEach(x -> this.exercises.add(new ExerciseSummaryResponse(x)));
    }
    if (entity.getAgroupament() != null) {
      this.agroupament = new AgroupamentSummaryResponse(entity.getAgroupament());
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

  public AgroupamentSummaryResponse getAgroupament() {
    return agroupament;
  }

  public void setAgroupament(AgroupamentSummaryResponse agroupament) {
    this.agroupament = agroupament;
  }

  public List<ExerciseSummaryResponse> getExercises() {
    return exercises;
  }

  public void setExercises(List<ExerciseSummaryResponse> exercises) {
    this.exercises = exercises;
  }

}
