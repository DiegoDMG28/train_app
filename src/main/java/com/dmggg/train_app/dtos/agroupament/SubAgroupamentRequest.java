package com.dmggg.train_app.dtos.agroupament;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubAgroupamentRequest {
  @NotBlank(message = "Name is required")
  private String name;

  private List<Long> exercises = new ArrayList<>();

  @NotNull(message = "Agroupament is required")
  private Long agroupament;

  public SubAgroupamentRequest() {
  }

  public SubAgroupamentRequest(String name, Long agroupament, List<Long> exercises) {
    this.name = name;
    this.agroupament = agroupament;
    this.exercises = exercises;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getAgroupament() {
    return agroupament;
  }

  public void setAgroupament(Long agroupament) {
    this.agroupament = agroupament;
  }

  public List<Long> getExercises() {
    return exercises;
  }

  public void setExercises(List<Long> exercises) {
    this.exercises = exercises;
  }

}
