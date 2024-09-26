package com.dog.health.dogbogamserver.domain.dogs.application.port.in;

import com.dog.health.dogbogamserver.domain.dogs.domain.Dog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogUseCase {
    Dog getDogById(Long id);
    List<Dog> getAllDogs();
    void registerDog(Dog dog);
}
