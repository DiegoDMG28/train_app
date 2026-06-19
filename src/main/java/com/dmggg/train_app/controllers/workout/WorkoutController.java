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

import com.dmggg.train_app.dtos.workout.WorkoutRequest;
import com.dmggg.train_app.dtos.workout.WorkoutResponse;
import com.dmggg.train_app.services.workout.WorkoutService;

@RestController
@RequestMapping(value = "/workouts")
public class WorkoutController {

  @Autowired
  private WorkoutService service;

  @GetMapping
  public ResponseEntity<List<WorkoutResponse>> searchAll() {
    return ResponseEntity.ok().body(service.searchAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<WorkoutResponse> searchById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.searchById(id));
  }

  @PostMapping
  public ResponseEntity<WorkoutResponse> insert(@RequestBody WorkoutRequest workoutRequest) {
    WorkoutResponse workoutResponse = service.insert(workoutRequest);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(workoutResponse.getId()).toUri();
    return ResponseEntity.created(uri).body(workoutResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<WorkoutResponse> update(@PathVariable Long id, @RequestBody WorkoutRequest workoutRequest) {
    return ResponseEntity.ok().body(service.update(id, workoutRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
