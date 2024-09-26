package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

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
    public void save(Dog dog) {
        DogEntity dogEntity = dogMapper.toEntity(dog);
        dogSpringDataRepository.save(dogEntity);
    }

    @Override
    public void update(Dog dog) {
        DogEntity dogEntity = dogMapper.toEntity(dog);
        dogSpringDataRepository.save(dogEntity); // JPA의 save는 업데이트도 처리
    }

    @Override
    public void deleteById(Long id) {
        dogSpringDataRepository.deleteById(id);
    }

    @Override
    public Optional<Dog> findByDogId(Long Dogid) {
        Optional<DogEntity> dogEntity = dogSpringDataRepository.findById(Dogid);
        return dogEntity.map(dogMapper::toDomain);
    }
}
