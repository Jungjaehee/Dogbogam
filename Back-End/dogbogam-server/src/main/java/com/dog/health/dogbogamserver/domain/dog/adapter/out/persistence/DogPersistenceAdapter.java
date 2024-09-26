package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.CreateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.UpdateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.DeleteDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogDetailsPort;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DogPersistenceAdapter implements CreateDogPort, UpdateDogPort, DeleteDogPort, FindDogDetailsPort {

    private final DogSpringDataRepository dogSpringDataRepository;
    private final DogMapper dogMapper;

    @Override
    public void save(CreateDogDTO createDogDTO) {
        DogEntity dogEntity = dogMapper.toEntity(createDogDTO);
        dogSpringDataRepository.save(dogEntity);
    }

    @Override
    public void update(Dog dog) {
        DogEntity dogEntity = dogMapper.toEntity(dog);
        dogSpringDataRepository.save(dogEntity);
    }

    @Override
    public void deleteById(Long dogId) {
//        dogSpringDataRepository.deleteById(id); // hard delete

    }

    @Override
    public Optional<Dog> findByDogId(Long dogId) {
        Optional<DogEntity> dogEntity = dogSpringDataRepository.findById(dogId);
        return dogEntity.map(dogMapper::toDomain);
    }
}
