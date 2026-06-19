package com.dmggg.train_app.entities.workout;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_workout")
public class Workout {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private Instant instant;

  @OneToMany(mappedBy = "workout")
  private List<WorkoutExercise> listWorkoutExercises;

  public Workout() {
  }

  public Workout(long id, String name, List<WorkoutExercise> listWorkoutExercises) {
    this.id = id;
    this.name = name;
    this.instant = Instant.now();
    this.listWorkoutExercises = listWorkoutExercises;
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

  public List<WorkoutExercise> getListWorkoutExercises() {
    return listWorkoutExercises;
  }

  public void setListWorkoutExercises(List<WorkoutExercise> listWorkoutExercises) {
    this.listWorkoutExercises = listWorkoutExercises;
  }

}
