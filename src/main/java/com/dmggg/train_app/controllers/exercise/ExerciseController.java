package com.dmggg.train_app.controllers.exercise;

import java.net.URI;
import java.util.List;

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

import com.dmggg.train_app.dtos.exercise.ExerciseRequest;
import com.dmggg.train_app.dtos.exercise.ExerciseResponse;
import com.dmggg.train_app.services.exercise.ExerciseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exercises")
public class ExerciseController {

  private final ExerciseService service;

  @GetMapping
  public ResponseEntity<List<ExerciseResponse>> searchAll() {
    return ResponseEntity.ok().body(service.searchAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExerciseResponse> searchById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.searchById(id));
  }

  @PostMapping
  public ResponseEntity<ExerciseResponse> insert(@Valid @RequestBody ExerciseRequest exerciseRequest) {
    ExerciseResponse exerciseResponse = service.insert(exerciseRequest);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(exerciseResponse.getId()).toUri();
    return ResponseEntity.created(uri).body(exerciseResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExerciseResponse> update(@PathVariable Long id, @Valid @RequestBody ExerciseRequest exerciseRequest) {
    return ResponseEntity.ok().body(service.update(id, exerciseRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
