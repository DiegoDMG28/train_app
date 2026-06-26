package com.dmggg.train_app.entities.exercise;

import java.util.ArrayList;
import java.util.List;

import com.dmggg.train_app.entities.agroupament.Agroupament;
import com.dmggg.train_app.entities.agroupament.SubAgroupament;
import com.dmggg.train_app.entities.workout.WorkoutExercise;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_exercise")
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;

  @OneToMany(
    mappedBy = "exercise",
    cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    orphanRemoval = true
  )
  private List<WorkoutExercise> listWorkoutExercises = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "tb_exercise_sub_agroupament", 
    joinColumns = @JoinColumn(name = "exercise_id"), 
    inverseJoinColumns = @JoinColumn(name = "sub_agroupament_id"))
  private List<SubAgroupament> listSubAgroupaments = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "tb_exercise_agroupament", 
    joinColumns = @JoinColumn(name = "exercise_id"), 
    inverseJoinColumns = @JoinColumn(name = "agroupament_id"))
  private List<Agroupament> listAgroupaments = new ArrayList<>();

  public Exercise() {
  }

  public Exercise(long id, String name, List<WorkoutExercise> listWorkoutExercises, List<SubAgroupament> listSubAgroupaments, List<Agroupament> listAgroupaments) {
    this.id = id;
    this.name = name;
    this.listWorkoutExercises = listWorkoutExercises;
    this.listSubAgroupaments = listSubAgroupaments;
    this.listAgroupaments = listAgroupaments;
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

  public List<WorkoutExercise> getListWorkoutExercises() {
    return listWorkoutExercises;
  }

  public void setListWorkoutExercises(List<WorkoutExercise> listWorkoutExercises) {
    this.listWorkoutExercises = listWorkoutExercises;
  }

  public List<SubAgroupament> getListSubAgroupaments() {
    return listSubAgroupaments;
  }

  public void setListSubAgroupaments(List<SubAgroupament> listSubAgroupaments) {
    this.listSubAgroupaments = listSubAgroupaments;
  }

  public List<Agroupament> getListAgroupaments() {
    return listAgroupaments;
  }

  public void setListAgroupaments(List<Agroupament> listAgroupaments) {
    this.listAgroupaments = listAgroupaments;
  }

}
