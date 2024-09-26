package com.dog.health.dogbogamserver.domain.dog.application.port.out;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogPersistencePort {
    Dog findById(Long id);
    List<Dog> findAll();
    void save(Dog dog);
}
