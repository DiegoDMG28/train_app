package com.dmggg.train_app.entities.workout;

import com.dmggg.train_app.entities.exercise.Exercise;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_workout_exercise")
public class WorkoutExercise {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int minReps;
  private int maxReps;

  @ManyToOne(optional = false)
  @JoinColumn(name = "exercise_id", nullable = false)
  private Exercise exercise;

  @ManyToOne(optional = false)
  @JoinColumn(name = "workout_id", nullable = false)
  private Workout workout;




  
  public WorkoutExercise() {
  }

  public WorkoutExercise(long id, int min_reps, int max_reps, Exercise exercise, Workout workout) {
    this.id       = id;
    this.minReps  = min_reps;
    this.maxReps  = max_reps;
    this.exercise = exercise;
    this.workout  = workout;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getMinReps() {
    return minReps;
  }

  public void setMinReps(int minReps) {
    this.minReps = minReps;
  }

  public int getMaxReps() {
    return maxReps;
  }

  public void setMaxReps(int maxReps) {
    this.maxReps = maxReps;
  }

  public Exercise getExercise() {
    return exercise;
  }

  public void setExercise(Exercise exercise) {
    this.exercise = exercise;
  }

  public Workout getWorkout() {
    return workout;
  }

  public void setWorkout(Workout workout) {
    this.workout = workout;
  }
  
  
}
