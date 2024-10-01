package com.dog.health.dogbogamserver.domain.dog.application.service;

import com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto.CreateDogRequestDTO;
import com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto.UpdateDogRequestDTO;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.*;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.CreateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogsPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.UpdateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogDetailsPort;
import com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto.DogDto;
import com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto.FindDogsResponseDto;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.member.application.service.MemberService;
import com.dog.health.dogbogamserver.domain.member.domain.Member;
import com.dog.health.dogbogamserver.global.auth.utils.JWTProvider;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void createDog(CreateDogRequestDTO createDogRequestDTO, Long memberId, MultipartFile image) {
        Member member = memberService.findByMemberId(memberId);
        Dog createDog = Dog.builder()
                .name(createDogRequestDTO.getName())
                .member(member)
                .gender(createDogRequestDTO.getGender())
                .breed(createDogRequestDTO.getBreed())
                .birthDate(strToLocalDate(createDogRequestDTO.getBirthDate()))
                .weight(createDogRequestDTO.getWeight())
                .isNeutered(createDogRequestDTO.getIsNeutered())
                .isDeleted(false)
                // 추후 이미지 추가
                .build();
        createDogPort.save(createDog);
    }

    @Override
    public void updateDog(UpdateDogRequestDTO updateDogRequestDTO, Long memberId, MultipartFile image) {
        Member member = memberService.findByMemberId(memberId);
        if(member.getMemberId() != updateDogRequestDTO.getMemberId()){
            throw new CustomException(ErrorCode.USER_VALIDATION_ERROR);
        }
        Optional<Dog> existingDog = findDogDetailsPort.findByDogId(updateDogRequestDTO.getDogId());
        if (existingDog.isPresent()) {
            Dog updatedDog = Dog.builder()
                    .member(member)
                    .breed(updateDogRequestDTO.getBreed())
                    .name(updateDogRequestDTO.getName())
                    .birthDate(strToLocalDate(updateDogRequestDTO.getBirthDate()))
                    .dogId(updateDogRequestDTO.getDogId())
                    .isNeutered(updateDogRequestDTO.getIsNeutered())
                    .weight(updateDogRequestDTO.getWeight())
                    .gender(updateDogRequestDTO.getGender())
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
    public Optional<FindDogsResponseDto> findDogsByMemberId(Long memberId, int page, int size) {
        List<Dog> dogs = findDogsPort.findDogsByMemberId(memberId).orElse(Collections.emptyList());

        Long totalElements = (long) dogs.size();
        Long totalPages = (totalElements + size - 1) / size;

        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, size); // page는 0부터 시작함
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), dogs.size());
        List<Dog> filteredDogs = dogs.subList(start, end);

        List<DogDto> dogDtos = filteredDogs.stream()
                .map(dog -> DogDto.builder()
                        .dogId(dog.getDogId())
                        .dogName(dog.getName())
                        .dogUrl(dog.getImageUrl())
                        .build())
                .collect(Collectors.toList());

        return Optional.of(FindDogsResponseDto.builder()
                .size(filteredDogs.size())
                .totalElements(totalElements)
                .currentPage((long) page + 1) // 클라이언트에 반환할 때는 1부터 시작
                .totalPages(totalPages)
                .dogList(dogDtos)
                .build());
    }

    public LocalDate strToLocalDate(String str) {
// 입력된 6자리 문자열(YYMMDD)로부터 연도, 월, 일을 추출하여 LocalDate로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");

        // LocalDate로 변환
        LocalDate date = LocalDate.parse(str, formatter);

        // 만약 연도를 2000년 이후로 판단하고 싶다면, 추가로 다음과 같은 조건을 적용
        if (date.getYear() < 2000) {
            date = date.plusYears(100);
        }

        return date;
    }
}
