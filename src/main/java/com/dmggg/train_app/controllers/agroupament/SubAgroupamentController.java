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

import com.dmggg.train_app.dtos.agroupament.SubAgroupamentRequest;
import com.dmggg.train_app.dtos.agroupament.SubAgroupamentResponse;
import com.dmggg.train_app.services.agroupament.SubAgroupamentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/subAgroupaments")
public class SubAgroupamentController {

  private final SubAgroupamentService service;

  @GetMapping
  public ResponseEntity<List<SubAgroupamentResponse>> searchAll() {
    return ResponseEntity.ok().body(service.searchAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubAgroupamentResponse> searchById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.searchById(id));
  }

  @PostMapping
  public ResponseEntity<SubAgroupamentResponse> insert(@RequestBody SubAgroupamentRequest subAgroupamentRequest) {
    SubAgroupamentResponse subAgroupamentResponse = service.insert(subAgroupamentRequest);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(subAgroupamentResponse.getId()).toUri();
    return ResponseEntity.created(uri).body(subAgroupamentResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SubAgroupamentResponse> update(@PathVariable Long id, @RequestBody SubAgroupamentRequest subAgroupamentRequest) {
    return ResponseEntity.ok().body(service.update(id, subAgroupamentRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
