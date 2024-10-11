package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DogSpringDataRepository extends JpaRepository<DogEntity, Long> {
    Optional<List<DogEntity>> findDogsByMember(MemberEntity memberEntity);
}
