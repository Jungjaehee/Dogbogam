package com.dog.health.dogbogamserver.domain.healthProblem.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.healthProblem.application.port.out.DeleteHealthProblemPort;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.out.LoadHealthProblemPort;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.out.SaveHealthProblemPort;
import com.dog.health.dogbogamserver.domain.healthProblem.domain.HealthProblem;
import com.dog.health.dogbogamserver.domain.dog.application.port.out. FindDogDetailsPort;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HealthProblemPersistenceAdapter implements LoadHealthProblemPort, SaveHealthProblemPort, DeleteHealthProblemPort {

    private final HealthProblemSpringDataRepository repository;
    private final HealthProblemMapper mapper;
    private final  FindDogDetailsPort findDogDetailsPort;

    @Override
    public List<HealthProblem> loadHealthProblemsByDogId(Long dogId) {
        return repository.findByDog_DogId(dogId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HealthProblem> loadHealthProblemById(Long healthProblemId) {
        return repository.findById(healthProblemId)
                .map(mapper::toDomain);
    }

    @Override
    public void saveHealthProblem(HealthProblem healthProblem) {
        repository.save(mapper.toEntity(healthProblem));
    }

    @Override
    public Optional<Dog> loadDogById(Long dogId) {
        return findDogDetailsPort.findByDogId(dogId);
    }

    @Override
    public void deleteHealthProblem(Long healthProblemId) {
        repository.deleteById(healthProblemId);
    }
}
