package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DogMapper {

    private final MemberMapper memberMapper; // MemberEntity -> Member 변환을 위한 Mapper

    public DogEntity toEntity(Dog dog) {

        return DogEntity.builder()
                .dogId(dog.getDogId())
                .member(memberMapper.toEntity(dog.getMember()))  // Dog 도메인 모델에서 Member 도메인 객체를 MemberEntity로 변환
                .name(dog.getName())
                .breed(dog.getBreed())
                .gender(dog.getGender())
                .birthDate(dog.getBirthDate())
                .weight(dog.getWeight())
                .isNeutered(dog.getIsNeutered())
                .imageName(dog.getImageName())
                .imageUrl(dog.getImageUrl())
                .isDeleted(dog.getIsDeleted())
                .build();
    }

    public Dog toDomain(DogEntity dogEntity) {
        return Dog.builder()
                .dogId(dogEntity.getDogId())
                .member(memberMapper.toDomain(dogEntity.getMember()))  // Member 도메인 객체를 사용
                .name(dogEntity.getName())
                .breed(dogEntity.getBreed())
                .gender(dogEntity.getGender())
                .birthDate(dogEntity.getBirthDate())
                .weight(dogEntity.getWeight())
                .isNeutered(dogEntity.getIsNeutered())
                .imageName(dogEntity.getImageName())
                .imageUrl(dogEntity.getImageUrl())
                .isDeleted(dogEntity.getIsDeleted())
                .build();
    }

    public List<Dog> entityListToDomainList(List<DogEntity> entityList) {
        List<Dog> dogList = new ArrayList<>();

        for (DogEntity dogEntity : entityList) {
            dogList.add(toDomain(dogEntity));
        }

        return dogList;
    }
}
