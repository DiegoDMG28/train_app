package com.dmggg.train_app.services.agroupament;

import com.dmggg.train_app.repositories.agroupament.SubAgroupamentRepository;
import com.dmggg.train_app.repositories.exercise.ExerciseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmggg.train_app.dtos.agroupament.AgroupamentRequest;
import com.dmggg.train_app.dtos.agroupament.AgroupamentResponse;
import com.dmggg.train_app.entities.agroupament.Agroupament;
import com.dmggg.train_app.entities.agroupament.SubAgroupament;
import com.dmggg.train_app.entities.exercise.Exercise;
import com.dmggg.train_app.repositories.agroupament.AgroupamentRepository;
import com.dmggg.train_app.services.exceptions.EntityNotFound;

@Service
public class AgroupamentService {

  private final SubAgroupamentRepository subAgroupamentRepository;
  private final ExerciseRepository exerciseRepository;
  @Autowired
  private AgroupamentRepository repository;

  AgroupamentService(ExerciseRepository exerciseRepository, SubAgroupamentRepository subAgroupamentRepository) {
    this.exerciseRepository = exerciseRepository;
    this.subAgroupamentRepository = subAgroupamentRepository;
  }

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
    List<Exercise> listExercises = new ArrayList<>();
    List<SubAgroupament> listSubAgroupaments = new ArrayList<>();

    agroupament.setName(agroupamentRequest.getName());

    if (!agroupamentRequest.getExercises().isEmpty() && agroupamentRequest.getExercises() != null) {
      agroupamentRequest.getExercises().forEach(x -> listExercises.add(exerciseRepository.findById(x)
        .orElseThrow(() -> new EntityNotFound("Exercise not found on our database"))));
    
      agroupament.setListExercises(listExercises);
    }

    if (!agroupamentRequest.getSubAgroupaments().isEmpty() && agroupamentRequest.getSubAgroupaments() != null) {
      agroupamentRequest.getSubAgroupaments().forEach(x -> listSubAgroupaments.add(subAgroupamentRepository.findById(x)
        .orElseThrow(() -> new EntityNotFound("SubAgroupament not found on our database"))));
    
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
