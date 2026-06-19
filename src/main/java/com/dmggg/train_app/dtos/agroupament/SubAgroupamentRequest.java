package com.dmggg.train_app.dtos.agroupament;

import java.util.ArrayList;
import java.util.List;

public class SubAgroupamentRequest {
  private String name;
  private Long agroupament;
  private List<Long> exercises = new ArrayList<>();

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
