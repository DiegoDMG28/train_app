package com.dmggg.train_app.entities.agroupament;

import java.util.ArrayList;
import java.util.List;

import com.dmggg.train_app.entities.exercise.Exercise;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sub_agroupament")
public class SubAgroupament {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  @ManyToOne(optional = false)
  @JoinColumn(name = "agroupament_id", nullable = false)
  private Agroupament agroupament;

  @ManyToMany(mappedBy = "listSubAgroupaments")
  private List<Exercise> listExercises = new ArrayList<>();

  public SubAgroupament() {
  }

  public SubAgroupament(long id, String name, Agroupament agroupament, List<Exercise> listExercises) {
    this.id = id;
    this.name = name;
    this.agroupament = agroupament;
    this.listExercises = listExercises;
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

  public Agroupament getAgroupament() {
    return agroupament;
  }

  public void setAgroupament(Agroupament agroupament) {
    this.agroupament = agroupament;
  }

  public List<Exercise> getListExercises() {
    return listExercises;
  }

  public void setListExercises(List<Exercise> listExercises) {
    this.listExercises = listExercises;
  }

  
}
