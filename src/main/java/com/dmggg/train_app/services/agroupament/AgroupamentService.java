package com.dmggg.train_app.services.agroupament;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmggg.train_app.dtos.agroupament.AgroupamentRequest;
import com.dmggg.train_app.dtos.agroupament.AgroupamentResponse;
import com.dmggg.train_app.entities.agroupament.Agroupament;
import com.dmggg.train_app.entities.agroupament.SubAgroupament;
import com.dmggg.train_app.entities.exercise.Exercise;
import com.dmggg.train_app.repositories.agroupament.AgroupamentRepository;
import com.dmggg.train_app.repositories.agroupament.SubAgroupamentRepository;
import com.dmggg.train_app.repositories.exercise.ExerciseRepository;
import com.dmggg.train_app.services.exceptions.EntityNotFound;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AgroupamentService {

  private final SubAgroupamentRepository subAgroupamentRepository;
  private final ExerciseRepository exerciseRepository;
  private final AgroupamentRepository repository;

  @Transactional(readOnly = true)
  public List<AgroupamentResponse> searchAll() {
    List<Agroupament> list = repository.findAll();
    List<AgroupamentResponse> listResponse = new ArrayList<>();
    list.forEach(x -> listResponse.add(new AgroupamentResponse(x)));
    return listResponse;
  }

  @Transactional(readOnly = true)
  public AgroupamentResponse searchById(Long id) {
    Optional<Agroupament> agroupament = repository.findById(id);
    return new AgroupamentResponse(agroupament.orElseThrow(()
        -> new EntityNotFound("Agroupament not found on our database")));
  }

  @Transactional
  public AgroupamentResponse insert(AgroupamentRequest agroupamentRequest) {
    Agroupament agroupament = new Agroupament();

    agroupament.setName(agroupamentRequest.getName());

    if (agroupamentRequest.getExercises() != null && !agroupamentRequest.getExercises().isEmpty()) {
      List<Exercise> listExercises = new ArrayList<>();
      agroupamentRequest.getExercises().forEach(id ->
        listExercises.add(exerciseRepository.findById(id)
          .orElseThrow(() -> new EntityNotFound("Exercise id :" + id + " not found on our database"))));
      agroupament.setListExercises(listExercises);
    }

    if (agroupamentRequest.getSubAgroupaments() != null && !agroupamentRequest.getSubAgroupaments().isEmpty()) {
      List<SubAgroupament> listSubAgroupaments = new ArrayList<>();
      agroupamentRequest.getSubAgroupaments().forEach(id ->
        listSubAgroupaments.add(subAgroupamentRepository.findById(id)
          .orElseThrow(() -> new EntityNotFound("SubAgroupament id :" + id + " not found on our database"))));
      agroupament.setListSubAgroupaments(listSubAgroupaments);
    }

    agroupament = repository.save(agroupament);
    
    return new AgroupamentResponse(agroupament);
  }

  @Transactional
  public AgroupamentResponse update(Long id, AgroupamentRequest agroupamentRequest) {
    Agroupament agroupament = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Agroupament not found on our database"));

    agroupament.setName(agroupamentRequest.getName());

    if (agroupamentRequest.getSubAgroupaments() != null) {
      List<SubAgroupament> listSubAgroupaments = new ArrayList<>();
    
      agroupamentRequest.getSubAgroupaments().forEach(x ->
        listSubAgroupaments.add(subAgroupamentRepository.findById(x)
          .orElseThrow(() -> new EntityNotFound("SubAgroupament id :" + x + " not found on our database"))));
    
      
      agroupament.getListSubAgroupaments().clear();
      agroupament.getListSubAgroupaments().addAll(listSubAgroupaments);
    }

    if (agroupamentRequest.getExercises() != null) {
      List<Exercise> listExercises = new ArrayList<>();
    
      agroupamentRequest.getExercises().forEach(x ->
        listExercises.add(exerciseRepository.findById(x)
          .orElseThrow(() -> new EntityNotFound("Exercise id :" + x + " not found on our database"))));
    
      agroupament.getListExercises().clear();
      agroupament.getListExercises().addAll(listExercises);
    }


    agroupament = repository.save(agroupament);
    return new AgroupamentResponse(agroupament);
  }

  @Transactional
  public void delete(Long id) {
    Agroupament agroupament = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Agroupament not found on our database"));
    repository.delete(agroupament);
  }
}
