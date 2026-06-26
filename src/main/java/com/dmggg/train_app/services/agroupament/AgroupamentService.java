package com.dmggg.train_app.services.agroupament;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
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
import com.dmggg.train_app.services.exceptions.DatabaseException;
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
    return new AgroupamentResponse(
        agroupament.orElseThrow(() -> new EntityNotFound("Agroupament not found on our database")));
  }

  @Transactional
  public AgroupamentResponse insert(AgroupamentRequest agroupamentRequest) {
    Agroupament agroupament = new Agroupament();

    agroupament.setName(agroupamentRequest.getName());

    if (agroupamentRequest.getExercises() != null && !agroupamentRequest.getExercises().isEmpty()) {
      agroupamentRequest.getExercises().forEach(x -> {
        Exercise exercise = exerciseRepository.findById(x)
            .orElseThrow(() -> new EntityNotFound("Exercise id :" + x + " not found on our database"));

        exercise.getListAgroupaments().add(agroupament);
        exerciseRepository.save(exercise);
      });
    }

    if (agroupamentRequest.getSubAgroupaments() != null && !agroupamentRequest.getSubAgroupaments().isEmpty()) {
      agroupamentRequest.getSubAgroupaments().forEach(x -> {
        SubAgroupament subAgroupament = subAgroupamentRepository.findById(x)
            .orElseThrow(() -> new EntityNotFound("SubAgroupament id :" + x + " not found on our database"));

        subAgroupament.setAgroupament(agroupament);
        subAgroupamentRepository.save(subAgroupament);
      });
    }

    Agroupament inserted = repository.save(agroupament);

    return new AgroupamentResponse(inserted);
  }

  @Transactional
  public AgroupamentResponse update(Long id, AgroupamentRequest agroupamentRequest) {
    Agroupament agroupament = repository.findById(id)
        .orElseThrow(() -> new EntityNotFound("Agroupament not found on our database"));

    agroupament.setName(agroupamentRequest.getName());

    if (agroupamentRequest.getSubAgroupaments() != null) {
      new ArrayList<>(agroupament.getListSubAgroupaments()).forEach(x -> {
        if (!agroupamentRequest.getSubAgroupaments().contains(x.getId())) {
          x.setAgroupament(null);
          subAgroupamentRepository.save(x);
        }
      });

      agroupamentRequest.getSubAgroupaments().forEach(x -> {
        SubAgroupament subAgroupament = subAgroupamentRepository.findById(x)
            .orElseThrow(() -> new EntityNotFound("SubAgroupament id :" + x + " not found on our database"));

        if (!agroupament.getListSubAgroupaments().contains(subAgroupament)) {
          subAgroupament.setAgroupament(agroupament);
          subAgroupamentRepository.save(subAgroupament);
        }

      });
    }

    if (agroupamentRequest.getExercises() != null) {
      new ArrayList<>(agroupament.getListExercises()).forEach(x -> {
        if (!agroupamentRequest.getExercises().contains(x.getId())) {
          x.getListAgroupaments().remove(agroupament);
          exerciseRepository.save(x);
        }
      });

      agroupamentRequest.getExercises().forEach(x -> {
        Exercise exercise = exerciseRepository.findById(x)
            .orElseThrow(() -> new EntityNotFound("Exercise id :" + x + " not found on our database"));

        if (!agroupament.getListExercises().contains(exercise)) {
          exercise.getListAgroupaments().add(agroupament);
          exerciseRepository.save(exercise);
        }

      });
    }

    Agroupament updated = repository.save(agroupament);
    return new AgroupamentResponse(updated);
  }

  @Transactional
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new EntityNotFound("Agroupament not found on our database");
    }
    try {

      Agroupament agroupament = repository.findById(id)
          .orElseThrow(() -> new EntityNotFound("Agroupament not found on our database"));

      new ArrayList<>(agroupament.getListSubAgroupaments()).forEach(x -> {
        x.setAgroupament(null);
        subAgroupamentRepository.save(x);
      });

      new ArrayList<>(agroupament.getListExercises()).forEach(x -> {
        x.getListAgroupaments().remove(agroupament);
        exerciseRepository.save(x);
      });

      repository.delete(agroupament);

    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Integrity violation");
    }

  }
}
