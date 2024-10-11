package com.dog.health.dogbogamserver.domain.dog.application.service;

import com.dog.health.dogbogamserver.domain.dog.application.port.out.*;
import com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto.CreateDogRequestDTO;
import com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto.UpdateDogRequestDTO;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.*;
import com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto.FindDogsDogInfoDto;
import com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto.FindDogResponseDto;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.healthProblem.application.service.HealthProblemService;
import com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.response.HealthProblemResponse;
import com.dog.health.dogbogamserver.domain.member.application.service.MemberService;
import com.dog.health.dogbogamserver.domain.member.domain.Member;
import com.dog.health.dogbogamserver.global.auth.utils.JWTProvider;
import com.dog.health.dogbogamserver.global.aws.service.AwsService;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DogService implements CreateDogUseCase, UpdateDogUseCase, DeleteDogUseCase, FindDogDetailsUseCase,
        FindDogsUseCase {

    private final CreateDogPort createDogPort;
    private final UpdateDogPort updateDogPort;
    private final FindDogDetailsPort findDogDetailsPort;
    private final FindDogsPort findDogsPort;
    private final MemberService memberService;
    private final HealthProblemService healthProblemService;
    private final AwsService awsService;
    private final JWTProvider jwtProvider;
    private final FindDogByDogIdPort findDogByDogIdPort;

    @Override
    public Map<String, Long> createDog(CreateDogRequestDTO createDogRequestDTO, Long memberId) throws IOException {
        Member member = memberService.findByMemberId(memberId);
        String path = "dog_image";
        Map<String, Object> uploadParam = awsService.uploadFile(createDogRequestDTO.getImage(),path);
        Dog createDog = Dog.builder()
                .name(createDogRequestDTO.getName())
                .member(member)
                .gender(createDogRequestDTO.getGender())
                .breed(createDogRequestDTO.getBreed())
                .birthDate(strToLocalDate(createDogRequestDTO.getBirthDate()))
                .weight(createDogRequestDTO.getWeight())
                .isNeutered(createDogRequestDTO.getIsNeutered())
                .imageName(uploadParam.get("s3FileName").toString())
                .imageUrl(uploadParam.get("uploadImageUrl").toString())
                .isDeleted(false)
                .build();
        Dog savedDog = createDogPort.save(createDog);

        Map<String, Long> response = new HashMap<>();
        response.put("dogId", savedDog.getDogId());

        return response;
    }

    @Override
    public void updateDog(UpdateDogRequestDTO updateDogRequestDTO, Long memberId) throws IOException {
        Member member = memberService.findByMemberId(memberId);

        String path = "dog_image";
        Map<String, Object> uploadParam = awsService.uploadFile(updateDogRequestDTO.getImage(),path);
        Optional<Dog> existingDog = findDogDetailsPort.findByDogId(updateDogRequestDTO.getDogId());
        if (existingDog.isPresent()) {
            Dog updatedDog = Dog.builder()
                    .dogId(updateDogRequestDTO.getDogId())
                    .member(member)
                    .isDeleted(existingDog.get().getIsDeleted())
                    .name(updateDogRequestDTO.getName())
                    .breed(updateDogRequestDTO.getBreed())
                    .gender(existingDog.get().getGender())
                    .birthDate(strToLocalDate(updateDogRequestDTO.getBirthDate()))
                    .weight(updateDogRequestDTO.getWeight())
                    .isNeutered(updateDogRequestDTO.getIsNeutered())
                    .imageName(uploadParam.get("s3FileName").toString())
                    .imageUrl(uploadParam.get("uploadImageUrl").toString())
                    .modifiedAt(LocalDateTime.now())
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
    public FindDogResponseDto findDogDetails(Long dogId) {
        Dog dog = findDogDetailsPort.findByDogId(dogId).orElseThrow(() -> new CustomException(ErrorCode.DOG_NOT_FOUND));

        List<HealthProblemResponse> healthProblems = healthProblemService.findHealthProblems(dog.getDogId());

        return FindDogResponseDto.builder()
                .healthProblems(healthProblems)
                .dogId(dog.getDogId())
                .name(dog.getName())
                .breed(dog.getBreed())
                .gender(dog.getGender())
                .birth(dog.getBirthDate())
                .weight(dog.getWeight())
                .imageUrl(dog.getImageUrl())
                .createdAt(dog.getCreatedAt())
                .build();
    }

    @Override
    public Optional<List<FindDogsDogInfoDto>> findDogsByMemberId(Long memberId) {
        // 전체 데이터 리스트를 Port에서 가져오기
        List<Dog> dogs = findDogsPort.findDogsByMemberId(memberId);

        // Dog 객체들을 DogDto로 변환
        List<FindDogsDogInfoDto> findDogsDogInfoDtos = dogs.stream()
                .map(this::convertToDogDto)  // 변환 메서드를 사용
                .collect(Collectors.toList());

        return Optional.of(findDogsDogInfoDtos);
    }


    public LocalDate strToLocalDate(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
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

    private FindDogsDogInfoDto convertToDogDto(Dog dog) {
        // Dog 객체를 DogDto로 변환
        return FindDogsDogInfoDto.builder()
                .dogId(dog.getDogId())
                .dogName(dog.getName())  // Dog의 이름을 DogDto의 이름 필드로 설정
                .imageUrl(dog.getImageUrl())  // Dog의 이미지 URL을 DogDto에 설정
                .build();
    }

    public Dog FindDogByDogId(Long dogId) {
        return findDogByDogIdPort.FindDogByDogId(dogId);
    }
}
