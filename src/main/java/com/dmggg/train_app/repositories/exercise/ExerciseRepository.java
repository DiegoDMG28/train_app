package com.dmggg.train_app.repositories.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmggg.train_app.entities.exercise.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
