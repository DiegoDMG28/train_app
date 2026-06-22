package com.dmggg.train_app.entities.agroupament;
import java.util.List;

import com.dmggg.train_app.entities.exercise.Exercise;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_agroupament")
public class Agroupament {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;

  @OneToMany(
    mappedBy = "agroupament",
    cascade = {CascadeType.PERSIST, CascadeType.MERGE}
  )
  private List<SubAgroupament> listSubAgroupaments;

  @ManyToMany(mappedBy = "listAgroupaments")
  private List<Exercise> listExercises;

  public Agroupament() {
  }

  public Agroupament(long id, String name, List<SubAgroupament> listSubAgroupaments, List<Exercise> listExercises) {
    this.id = id;
    this.name = name;
    this.listSubAgroupaments = listSubAgroupaments;
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

  public List<SubAgroupament> getListSubAgroupaments() {
    return listSubAgroupaments;
  }

  public void setListSubAgroupaments(List<SubAgroupament> listSubAgroupaments) {
    this.listSubAgroupaments = listSubAgroupaments;
  }

  public List<Exercise> getListExercises() {
    return listExercises;
  }

  public void setListExercises(List<Exercise> listExercises) {
    this.listExercises = listExercises;
  }
  
}