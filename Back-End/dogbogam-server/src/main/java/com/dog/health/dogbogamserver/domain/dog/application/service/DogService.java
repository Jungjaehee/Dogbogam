package com.dog.health.dogbogamserver.domain.dog.application.service;

import com.dog.health.dogbogamserver.domain.dog.application.port.in.CreateDogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.UpdateDogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.DeleteDogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.FindDogDetailsUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.CreateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.UpdateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.DeleteDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogDetailsPort;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogService implements CreateDogUseCase, UpdateDogUseCase, DeleteDogUseCase, FindDogDetailsUseCase {

    private final CreateDogPort createDogPort;
    private final UpdateDogPort updateDogPort;
    private final DeleteDogPort deleteDogPort;
    private final FindDogDetailsPort findDogDetailsPort;

    @Override
    public void createDog(Dog dog) {
        createDogPort.save(dog);
    }

    @Override
    public void updateDog(Long id, Dog dog) {
        Optional<Dog> existingDog = findDogDetailsPort.findByDogId(id);
        if (existingDog.isPresent()) {
            Dog updatedDog = existingDog.get();
            // 필요한 값 업데이트
            updatedDog.setName(dog.getName());
            updatedDog.setBreed(dog.getBreed());
            updateDogPort.update(updatedDog);
        } else {
            throw new IllegalArgumentException("Dog with ID " + id + " not found");
        }
    }

    @Override
    public void deleteDog(Long id) {
        deleteDogPort.deleteById(id);
    }

    @Override
    public Optional<Dog> findDogDetails(Long Dogid) {
        return findDogDetailsPort.findByDogId(Dogid);
    }
}
