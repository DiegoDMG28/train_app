package com.dmggg.train_app.repositories.workout;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmggg.train_app.entities.workout.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}
