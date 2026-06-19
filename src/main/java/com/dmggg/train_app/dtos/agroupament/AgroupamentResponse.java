package com.dmggg.train_app.dtos.agroupament;

import java.util.ArrayList;
import java.util.List;

import com.dmggg.train_app.dtos.exercise.ExerciseSummaryResponse;
import com.dmggg.train_app.entities.agroupament.Agroupament;

public class AgroupamentResponse {
  private long id;
  private String name;
  private List<ExerciseSummaryResponse> exercises = new ArrayList<>();
  private List<SubAgroupamentSummaryResponse> subAgroupaments = new ArrayList<>();

  public AgroupamentResponse() {
  }

  public AgroupamentResponse(long id, String name, List<ExerciseSummaryResponse> exercises,
      List<SubAgroupamentSummaryResponse> subAgroupaments) {
    this.id = id;
    this.name = name;
    this.exercises = exercises;
    this.subAgroupaments = subAgroupaments;
  }

  public AgroupamentResponse(Agroupament entity) {
    this.id = entity.getId();
    this.name = entity.getName();

    if (entity.getListExercises() != null) {
      entity.getListExercises().forEach(x -> this.exercises.add(new ExerciseSummaryResponse(x)));
    }
    if (entity.getListSubAgroupaments() != null) {
      entity.getListSubAgroupaments().forEach(x -> this.subAgroupaments.add(new SubAgroupamentSummaryResponse(x)));
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

  public List<ExerciseSummaryResponse> getExercises() {
    return exercises;
  }

  public void setExercises(List<ExerciseSummaryResponse> exercises) {
    this.exercises = exercises;
  }

  public List<SubAgroupamentSummaryResponse> getSubAgroupaments() {
    return subAgroupaments;
  }

  public void setSubAgroupaments(List<SubAgroupamentSummaryResponse> subAgroupaments) {
    this.subAgroupaments = subAgroupaments;
  }

  
}
