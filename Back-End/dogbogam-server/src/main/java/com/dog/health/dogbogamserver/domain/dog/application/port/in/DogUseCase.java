package com.dog.health.dogbogamserver.domain.dog.application.port.in;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogUseCase {
    Dog getDogById(Long id);
    List<Dog> getAllDogs();
    void registerDog(Dog dog);
}
