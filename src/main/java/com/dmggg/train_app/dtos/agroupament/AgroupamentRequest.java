package com.dmggg.train_app.dtos.agroupament;

import java.util.ArrayList;
import java.util.List;

public class AgroupamentRequest {
  private String name;
  private List<Long> exercises = new ArrayList<>();
  private List<Long> subAgroupaments = new ArrayList<>();

  public AgroupamentRequest() {
  }

  public AgroupamentRequest(String name, List<Long> exercises, List<Long> subAgroupaments) {
    this.name = name;
    this.exercises = exercises;
    this.subAgroupaments = subAgroupaments;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Long> getExercises() {
    return exercises;
  }

  public void setExercises(List<Long> exercises) {
    this.exercises = exercises;
  }

  public List<Long> getSubAgroupaments() {
    return subAgroupaments;
  }

  public void setSubAgroupaments(List<Long> subAgroupaments) {
    this.subAgroupaments = subAgroupaments;
  }

}
