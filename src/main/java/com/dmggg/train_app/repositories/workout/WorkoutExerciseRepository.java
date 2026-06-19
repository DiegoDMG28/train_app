package com.dmggg.train_app.repositories.workout;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmggg.train_app.entities.workout.WorkoutExercise;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

}
