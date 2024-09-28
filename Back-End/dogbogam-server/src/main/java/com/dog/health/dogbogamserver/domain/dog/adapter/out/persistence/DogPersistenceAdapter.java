package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.application.port.out.*;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberEntity;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberMapper;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DogPersistenceAdapter implements CreateDogPort, UpdateDogPort, DeleteDogPort, FindDogDetailsPort
, FindDogsPort {

    private final DogSpringDataRepository dogSpringDataRepository;
    private final DogMapper dogMapper;
    private final MemberPersistenceAdapter memberPersistenceAdapter;
    private final MemberMapper memberMapper;

    @Override
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
    public Optional<List<Dog>> findDogsByMemberId(Long memberId) {
        MemberEntity memberEntity = memberMapper.toEntity(memberPersistenceAdapter.loadMember(memberId));
        return Optional.ofNullable(dogMapper.entityListToDomainList(dogSpringDataRepository.findDogsByMember(memberEntity)
                .orElseThrow(() -> new IllegalArgumentException("없는 멤버 입니다."))));
    }
}
