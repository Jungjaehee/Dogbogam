package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DogMapper {
//    private final MemberService memberService; MemberService 작성 후 사용

    public DogEntity toEntity(Dog dog) {
        // 삭제된 반려견이라면 null을 반환
        if(dog.getIsDeleted())
            return null;
        else
            return DogEntity.builder()
                    .dogId(dog.getDogId())
//                    .member()
                    .name(dog.getName())
                    .breed(dog.getBreed())
                    .gender(dog.getGender())
                    .birthDate(dog.getBirthDate())
                    .weight(dog.getWeight())
                    .isNeutered(dog.getIsNeutered())
                    .imageName(dog.getImageName())
                    .imageUrl(dog.getImageUrl())
                    .build();
    }

    public Dog toDomain(DogEntity entity) {
        if(entity.getIsDeleted())
            return null;
        else
            return Dog.builder()
                    .dogId(entity.getDogId())
//                    .member()
                    .name(entity.getName())
                    .breed(entity.getBreed())
                    .gender(entity.getGender())
                    .birthDate(entity.getBirthDate())
                    .weight(entity.getWeight())
                    .isNeutered(entity.getIsNeutered())
                    .imageName(entity.getImageName())
                    .imageUrl(entity.getImageUrl())
                    .build();
    }
}
