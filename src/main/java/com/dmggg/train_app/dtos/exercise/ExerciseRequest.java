package com.dmggg.train_app.dtos.exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseRequest {
  private String name;
  private List<Long> agroupaments = new ArrayList<>();
  private List<Long> subAgroupaments = new ArrayList<>();
  private List<Long> workoutExercises = new ArrayList<>();

  public ExerciseRequest() {
  }

  public ExerciseRequest(String name, List<Long> agroupaments, List<Long> subAgroupaments,
      List<Long> workoutExercises) {
    this.name = name;
    this.agroupaments = agroupaments;
    this.subAgroupaments = subAgroupaments;
    this.workoutExercises = workoutExercises;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Long> getAgroupaments() {
    return agroupaments;
  }

  public void setAgroupaments(List<Long> agroupaments) {
    this.agroupaments = agroupaments;
  }

  public List<Long> getSubAgroupaments() {
    return subAgroupaments;
  }

  public void setSubAgroupaments(List<Long> subAgroupaments) {
    this.subAgroupaments = subAgroupaments;
  }

  public List<Long> getWorkoutExercises() {
    return workoutExercises;
  }

  public void setWorkoutExercises(List<Long> workoutExercises) {
    this.workoutExercises = workoutExercises;
  }

}
