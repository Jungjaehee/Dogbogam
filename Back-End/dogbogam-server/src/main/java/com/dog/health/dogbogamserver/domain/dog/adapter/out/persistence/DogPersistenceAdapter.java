package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.application.port.out.*;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberEntity;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberSpringDataRepository;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.dog.health.dogbogamserver.global.web.exception.ErrorCode.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class DogPersistenceAdapter implements CreateDogPort, UpdateDogPort, DeleteDogPort, FindDogDetailsPort,
        FindDogsPort {

    private final MemberSpringDataRepository memberSpringDataRepository;
    private final DogSpringDataRepository dogSpringDataRepository;
    private final DogMapper dogMapper;

    @Override
    @Transactional
    public void save(Dog dog) {
        DogEntity dogEntity = dogMapper.toEntity(dog);
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

    @Override
    public Optional<DogEntity> findEntityByDogId(Long dogId) {
        return dogSpringDataRepository.findById(dogId);
    }

    @Override
    public Optional<List<Dog>> findDogsByMemberId(Long memberId) {
        MemberEntity memberEntity = memberSpringDataRepository.findById(memberId).orElseThrow(()->new CustomException(USER_NOT_FOUND));
        Optional<List<DogEntity>> dogList = dogSpringDataRepository.findDogsByMember(memberEntity);
        return Optional.ofNullable(dogMapper.entityListToDomainList(dogList.get()));
    }
}
