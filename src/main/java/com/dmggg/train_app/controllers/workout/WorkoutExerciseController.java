package com.dmggg.train_app.controllers.workout;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dmggg.train_app.dtos.workout.WorkoutExerciseRequest;
import com.dmggg.train_app.dtos.workout.WorkoutExerciseResponse;
import com.dmggg.train_app.services.workout.WorkoutExerciseService;

@RestController
@RequestMapping(value = "/workoutExercises")
public class WorkoutExerciseController {

  @Autowired
  private WorkoutExerciseService service;

  @GetMapping
  public ResponseEntity<List<WorkoutExerciseResponse>> searchAll() {
    return ResponseEntity.ok().body(service.searchAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<WorkoutExerciseResponse> searchById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.searchById(id));
  }

  @PostMapping
  public ResponseEntity<WorkoutExerciseResponse> insert(@RequestBody WorkoutExerciseRequest workoutExerciseRequest) {
    WorkoutExerciseResponse workoutExerciseResponse = service.insert(workoutExerciseRequest);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(workoutExerciseResponse.getId()).toUri();
    return ResponseEntity.created(uri).body(workoutExerciseResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<WorkoutExerciseResponse> update(@PathVariable Long id, @RequestBody WorkoutExerciseRequest workoutExerciseRequest) {
    return ResponseEntity.ok().body(service.update(id, workoutExerciseRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
