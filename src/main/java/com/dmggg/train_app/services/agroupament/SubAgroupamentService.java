package com.dmggg.train_app.services.agroupament;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmggg.train_app.dtos.agroupament.SubAgroupamentRequest;
import com.dmggg.train_app.dtos.agroupament.SubAgroupamentResponse;
import com.dmggg.train_app.dtos.exercise.ExerciseSummaryResponse;
import com.dmggg.train_app.entities.agroupament.SubAgroupament;
import com.dmggg.train_app.entities.exercise.Exercise;
import com.dmggg.train_app.repositories.agroupament.AgroupamentRepository;
import com.dmggg.train_app.repositories.agroupament.SubAgroupamentRepository;
import com.dmggg.train_app.repositories.exercise.ExerciseRepository;
import com.dmggg.train_app.services.exceptions.EntityNotFound;

@Service
public class SubAgroupamentService {

  @Autowired
  private SubAgroupamentRepository repository;

  @Autowired
  private AgroupamentRepository agroupamentRepository;

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Transactional(readOnly = true)
  public List<SubAgroupamentResponse> searchAll() {
    List<SubAgroupament> list = repository.findAll();
    List<SubAgroupamentResponse> listResponse = new ArrayList<>();
    list.forEach(x -> listResponse.add(new SubAgroupamentResponse(x)));
    return listResponse;
  }

  @Transactional(readOnly = true)
  public SubAgroupamentResponse searchById(Long id) {
    Optional<SubAgroupament> subAgroupament = repository.findById(id);
    return new SubAgroupamentResponse(subAgroupament.orElseThrow(()
        -> new EntityNotFound("SubAgroupament not found on our database")));
  }

  @Transactional
  public SubAgroupamentResponse insert(SubAgroupamentRequest subAgroupamentRequest) {
    SubAgroupament subAgroupament = new SubAgroupament();
    List<Exercise> listExercises = new ArrayList<>();

    subAgroupament.setName(subAgroupamentRequest.getName());

    subAgroupament.setAgroupament(agroupamentRepository.findById(subAgroupamentRequest.getAgroupament())
    .orElseThrow(() -> new EntityNotFound("Agroupament not found on our database")));

    if (!subAgroupamentRequest.getExercises().isEmpty() && subAgroupamentRequest.getExercises() != null) {
      subAgroupamentRequest.getExercises().forEach(x -> listExercises.add(exerciseRepository.findById(x)
        .orElseThrow(() -> new EntityNotFound("Exercise not found on our database"))));
    
      subAgroupament.setListExercises(listExercises);
    }

    subAgroupament = repository.save(subAgroupament);

    return new SubAgroupamentResponse(subAgroupament);
  }

  @Transactional
  public SubAgroupamentResponse update(Long id, SubAgroupamentRequest subAgroupamentRequest) {
    SubAgroupament subAgroupament = repository.findById(id)
      .orElseThrow(() -> new EntityNotFound("SubAgroupament not found on our database"));
    subAgroupament.setName(subAgroupamentRequest.getName());
    subAgroupament = repository.save(subAgroupament);
    return new SubAgroupamentResponse(subAgroupament);
  }

  @Transactional
  public void delete(Long id) {
    SubAgroupament subAgroupament = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("SubAgroupament not found on our database"));
    repository.delete(subAgroupament);
  }
}
