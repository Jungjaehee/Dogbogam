package com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberEntity;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberMapper;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.member.adapter.out.persistence.MemberSpringDataRepository;
import com.dog.health.dogbogamserver.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DogMapper {

    private final MemberPersistenceAdapter memberPersistenceAdapter;
    private final MemberMapper memberMapper; // MemberEntity -> Member 변환을 위한 Mapper

    public DogEntity toEntity(CreateDogDTO createDogDTO) {
        Member member = memberPersistenceAdapter.loadMember(createDogDTO.getMemberId());
        return DogEntity.builder()
                .member(memberMapper.toEntity(member))
                .name(createDogDTO.getName())
                .breed(createDogDTO.getBreed())
                .gender(createDogDTO.getGender())
                .birthDate(createDogDTO.getBirthDate())
                .weight(createDogDTO.getWeight())
                .isNeutered(createDogDTO.getIsNeutered())
                .imageName(createDogDTO.getImageName())
                .imageUrl(createDogDTO.getImageUrl())
                .isDeleted(false)  // 새로 생성된 반려견은 삭제되지 않은 상태
                .build();
    }

    public DogEntity toEntity(Dog dog) {
        Member member = memberPersistenceAdapter.loadMember(dog.getMember().getMemberId());

        return DogEntity.builder()
                .dogId(dog.getDogId())
                .member(memberMapper.toEntity(member))  // Dog 도메인 모델에서 Member 도메인 객체를 MemberEntity로 변환
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
        // MemberEntity를 Member로 변환
        Member member = memberMapper.toDomain(dogEntity.getMember());

        return Dog.builder()
                .dogId(dogEntity.getDogId())
                .member(member)  // Member 도메인 객체를 사용
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
}
