package com.dog.health.dogbogamserver.domain.dogs.application.port.out;

import com.dog.health.dogbogamserver.domain.dogs.domain.Dog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogPersistencePort {
    Dog findById(Long id);
    List<Dog> findAll();
    void save(Dog dog);
}
