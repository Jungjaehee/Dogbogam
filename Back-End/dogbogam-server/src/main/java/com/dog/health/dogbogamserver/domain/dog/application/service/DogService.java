package com.dog.health.dogbogamserver.domain.dog.application.service;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.UpdateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.*;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.CreateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogsPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.UpdateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogDetailsPort;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.member.application.service.MemberService;
import com.dog.health.dogbogamserver.domain.member.domain.Member;
import com.dog.health.dogbogamserver.global.auth.utils.JWTProvider;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogService implements CreateDogUseCase, UpdateDogUseCase, DeleteDogUseCase, FindDogDetailsUseCase,
        FindDogsUseCase {

    private final CreateDogPort createDogPort;
    private final UpdateDogPort updateDogPort;
    private final FindDogDetailsPort findDogDetailsPort;
    private final FindDogsPort findDogsPort;
    private final MemberService memberService;
    private final JWTProvider jwtProvider;

    @Override
    public void createDog(CreateDogDTO createDogDTO, Long memberId) {
        Member member = memberService.findByMemberId(memberId);
        Dog createDog = Dog.builder()
                .name(createDogDTO.getName())
                .member(member)
                .gender(createDogDTO.getGender())
                .breed(createDogDTO.getBreed())
                .birthDate(createDogDTO.getBirthDate())
                .weight(createDogDTO.getWeight())
                .isNeutered(createDogDTO.getIsNeutered())
                .isDeleted(false)
                // 추후 이미지 추가
                .build();
        createDogPort.save(createDog);
    }

    @Override
    public void updateDog(UpdateDogDTO updateDogDTO, Long memberId) {
        Member member = memberService.findByMemberId(memberId);
        if(member.getMemberId() != updateDogDTO.getMemberId()){
            throw new CustomException(ErrorCode.USER_VALIDATION_ERROR);
        }
        Optional<Dog> existingDog = findDogDetailsPort.findByDogId(updateDogDTO.getDogId());
        if (existingDog.isPresent()) {
            Dog updatedDog = Dog.builder()
                    .member(member)
                    .breed(updateDogDTO.getBreed())
                    .name(updateDogDTO.getName())
                    .birthDate(updateDogDTO.getBirthDate())
                    .dogId(updateDogDTO.getDogId())
                    .isNeutered(updateDogDTO.getIsNeutered())
                    .weight(updateDogDTO.getWeight())
                    .gender(updateDogDTO.getGender())
                    .isDeleted(existingDog.get().getIsDeleted())
                    .build();
            updateDogPort.update(updatedDog);
        } else {
            throw new CustomException(ErrorCode.DOG_NOT_FOUND);
        }
    }

    @Override
    public void deleteDog(Long dogId) {
        Optional<Dog> deleteDog = findDogDetailsPort.findByDogId(dogId);
        if (deleteDog.isPresent()) {
            deleteDog.get().deleteDog();
            updateDogPort.update(deleteDog.get());
        }
        // 이미 지워진 dog이거나 없는 dogId에 대한 예외처리 필요
    }

    @Override
    public Optional<Dog> findDogDetails(Long dogId) {
        return findDogDetailsPort.findByDogId(dogId);
    }

    @Override
    public Optional<List<Dog>> findDogsByMemberId(Long memberId) {
        return findDogsPort.findDogsByMemberId(memberId);
    }
}
