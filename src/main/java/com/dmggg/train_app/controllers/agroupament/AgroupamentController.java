package com.dmggg.train_app.controllers.agroupament;

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

import com.dmggg.train_app.dtos.agroupament.AgroupamentRequest;
import com.dmggg.train_app.dtos.agroupament.AgroupamentResponse;
import com.dmggg.train_app.services.agroupament.AgroupamentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/agroupaments")
public class AgroupamentController {

  private final AgroupamentService service;

  @GetMapping
  public ResponseEntity<List<AgroupamentResponse>> searchAll() {
    return ResponseEntity.ok().body(service.searchAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<AgroupamentResponse> searchById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.searchById(id));
  }

  @PostMapping
  public ResponseEntity<AgroupamentResponse> insert(@Valid @RequestBody AgroupamentRequest agroupamentRequest) {
    AgroupamentResponse agroupamentResponse = service.insert(agroupamentRequest);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(agroupamentResponse.getId()).toUri();
    return ResponseEntity.created(uri).body(agroupamentResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AgroupamentResponse> update(@PathVariable Long id, @Valid @RequestBody AgroupamentRequest agroupamentRequest) {
    return ResponseEntity.ok().body(service.update(id, agroupamentRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
