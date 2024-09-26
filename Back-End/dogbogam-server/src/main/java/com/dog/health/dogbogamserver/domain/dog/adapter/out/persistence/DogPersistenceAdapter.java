package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.application.port.out.DogPersistencePort;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DogPersistenceAdapter implements DogPersistencePort {
    private final DogSpringDataRepository repository;
    private final DogMapper mapper;

    @Override
    public Dog findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Dog not found"));
    }

    @Override
    public List<Dog> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Dog dog) {
        repository.save(mapper.toEntity(dog));
    }
}
