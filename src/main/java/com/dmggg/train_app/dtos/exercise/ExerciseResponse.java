package com.dmggg.train_app.dtos.exercise;

import java.util.ArrayList;
import java.util.List;

import com.dmggg.train_app.dtos.agroupament.AgroupamentSummaryResponse;
import com.dmggg.train_app.dtos.agroupament.SubAgroupamentSummaryResponse;
import com.dmggg.train_app.dtos.workout.WorkoutExerciseSummaryResponse;
import com.dmggg.train_app.entities.exercise.Exercise;

public class ExerciseResponse {
  private long id;
  private String name;
  private List<SubAgroupamentSummaryResponse> subAgroupaments = new ArrayList<>();
  private List<AgroupamentSummaryResponse> agroupaments = new ArrayList<>();
  private List<WorkoutExerciseSummaryResponse> workoutExercises = new ArrayList<>();

  public ExerciseResponse() {
  }

  public ExerciseResponse(long id, String name, List<SubAgroupamentSummaryResponse> subAgroupaments,
      List<AgroupamentSummaryResponse> agroupaments, List<WorkoutExerciseSummaryResponse> workoutExercises) {
    this.id = id;
    this.name = name;
    this.subAgroupaments = subAgroupaments;
    this.agroupaments = agroupaments;
    this.workoutExercises = workoutExercises;
  }

  public ExerciseResponse(Exercise entity) {
    this.id = entity.getId();
    this.name = entity.getName();

    if (entity.getListSubAgroupaments() != null) {
      entity.getListSubAgroupaments().forEach(x -> this.subAgroupaments.add(new SubAgroupamentSummaryResponse(x)));
    }
    if (entity.getListAgroupaments() != null) {
      entity.getListAgroupaments().forEach(x -> this.agroupaments.add(new AgroupamentSummaryResponse(x)));
    }
    if (entity.getListWorkoutExercises() != null) {
      entity.getListWorkoutExercises().forEach(x -> this.workoutExercises.add(new WorkoutExerciseSummaryResponse(x)));
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

  public List<SubAgroupamentSummaryResponse> getSubAgroupaments() {
    return subAgroupaments;
  }

  public void setSubAgroupaments(List<SubAgroupamentSummaryResponse> subAgroupaments) {
    this.subAgroupaments = subAgroupaments;
  }

  public List<AgroupamentSummaryResponse> getAgroupaments() {
    return agroupaments;
  }

  public void setAgroupaments(List<AgroupamentSummaryResponse> agroupaments) {
    this.agroupaments = agroupaments;
  }

  public List<WorkoutExerciseSummaryResponse> getWorkoutExercises() {
    return workoutExercises;
  }

  public void setWorkoutExercises(List<WorkoutExerciseSummaryResponse> workoutExercises) {
    this.workoutExercises = workoutExercises;
  }

}
