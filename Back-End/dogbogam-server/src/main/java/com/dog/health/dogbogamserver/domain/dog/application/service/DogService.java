package com.dog.health.dogbogamserver.domain.dog.application.service;

import com.dog.health.dogbogamserver.domain.dog.application.port.in.DogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.DogPersistencePort;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService implements DogUseCase {
    private final DogPersistencePort dogPersistencePort;

    @Override
    public Dog getDogById(Long id) {
        return dogPersistencePort.findById(id);
    }

    @Override
    public List<Dog> getAllDogs() {
        return dogPersistencePort.findAll();
    }

    @Override
    public void registerDog(Dog dog) {
        dogPersistencePort.save(dog);
    }
}
