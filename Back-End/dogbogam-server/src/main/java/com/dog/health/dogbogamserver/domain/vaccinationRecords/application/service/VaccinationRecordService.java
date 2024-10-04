package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service;


import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.adapter.out.persistence.VaccinationRecordEntity;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in.*;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.out.*;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.CreateVaccinationRecordRequestDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.UpdateVaccinationRecordRequestDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.response.FindVaccinationRecordResponseDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;
import com.dog.health.dogbogamserver.global.aws.service.AwsService;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaccinationRecordService implements CreateVaccinationRecordUseCase, UpdateVaccinationRecordUseCase,
        FindVaccinationRecordUseCase, DeleteVaccinationRecordUseCase, FindVaccinationRecordsUseCase {
    private final CreateVaccinationRecordPort createVaccinationRecordPort;
    private final UpdateVaccinationRecordPort updateVaccinationRecordPort;
    private final FindVaccinationRecordPort findVaccinationRecordPort;
    private final DeleteVaccinationRecordPort deleteVaccinationRecordPort;
    private final FindVaccinationRecordsPort findVaccinationRecordsPort;
    private final DogPersistenceAdapter dogPersistenceAdapter;
    private final AwsService awsService;

    @Override
    public void createVaccinationRecord(CreateVaccinationRecordRequestDto createVaccinationRecordRequestDto) {
        log.info("Service Create record : {}", createVaccinationRecordRequestDto);
        VaccinationRecordEntity vaccinationRecordEntity = VaccinationRecordEntity.builder()
                .recordTime(createVaccinationRecordRequestDto.getRecordTime())
                .dog(dogPersistenceAdapter.findEntityByDogId(createVaccinationRecordRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견 입니다.")))
                .content(createVaccinationRecordRequestDto.getContent())
                .hospital(createVaccinationRecordRequestDto.getHospital())
                .cost(createVaccinationRecordRequestDto.getCost())
                .vaccinationRound(createVaccinationRecordRequestDto.getVaccinationRound())
                .build();
        createVaccinationRecordPort.createVaccinationRecord(vaccinationRecordEntity);
    }

    @Override
    public void updateVaccinationRecord(UpdateVaccinationRecordRequestDto updateVaccinationRecordRequestDto) {
        log.info("Service Update record : {}", updateVaccinationRecordRequestDto);
        VaccinationRecordEntity vaccinationRecordEntity = VaccinationRecordEntity.builder()
                .vaccinationRecordId(updateVaccinationRecordRequestDto.getVaccinationRecordId())
                .recordTime(updateVaccinationRecordRequestDto.getRecordTime())
                .dog(dogPersistenceAdapter.findEntityByDogId(updateVaccinationRecordRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견입니다.")))
                .content(updateVaccinationRecordRequestDto.getContent())
                .hospital(updateVaccinationRecordRequestDto.getHospital())
                .cost(updateVaccinationRecordRequestDto.getCost())
                .vaccinationRound(updateVaccinationRecordRequestDto.getVaccinationRound())
                .build();
        updateVaccinationRecordPort.updateVaccinationRecord(vaccinationRecordEntity);
    }

    @Override
    public FindVaccinationRecordResponseDto findVaccinationRecordById(Long vaccinationRecordId) {
        log.info("Service Find record : {}", vaccinationRecordId);
        VaccinationRecord vaccinationRecord = findVaccinationRecordPort.findVaccinationRecordById(vaccinationRecordId)
                .orElseThrow(()-> new IllegalArgumentException("없는 리포트입니다."));

        return FindVaccinationRecordResponseDto.builder()
                .dogId(vaccinationRecord.getDog().getDogId())
                .recordTime(vaccinationRecord.getRecordTime())
                .content(vaccinationRecord.getContent())
                .hospital(vaccinationRecord.getHospital())
                .imageUrl(vaccinationRecord.getImageUrl())
                .cost(vaccinationRecord.getCost())
                .vaccinationRound(vaccinationRecord.getVaccinationRound())
                .build();
    }

    @Override
    public void deleteVaccinationRecord(Long vaccinationRecordId) throws IOException {
        log.info("Service Delete record : {}", vaccinationRecordId);
        VaccinationRecord vaccinationRecord = findVaccinationRecordPort.findVaccinationRecordById(vaccinationRecordId)
                .orElseThrow(()-> new CustomException(ErrorCode.VACCINATION_NOT_FOUND));
        if(vaccinationRecord.getImageName() != null) {
            awsService.deleteFile(vaccinationRecord.getImageName());
        }
        deleteVaccinationRecordPort.deleteVaccinationRecord(vaccinationRecordId);
    }

    @Override
    public List<FindVaccinationRecordResponseDto> findVaccinationsByDogId(Long dogId) {
        log.info("Senrvice Find records : {}", dogId);
        List<VaccinationRecord> vaccinationRecords = findVaccinationRecordsPort.findVaccinationRecordsByDogId(dogId)
                .orElseThrow(()->new IllegalArgumentException("없는 접종 기록입니다."));

        List<FindVaccinationRecordResponseDto> recordResponseDtos = new ArrayList<>();
        for (VaccinationRecord vaccinationRecord : vaccinationRecords) {
            recordResponseDtos.add(FindVaccinationRecordResponseDto.builder()
                    .dogId(vaccinationRecord.getDog().getDogId())
                    .recordTime(vaccinationRecord.getRecordTime())
                    .content(vaccinationRecord.getContent())
                    .hospital(vaccinationRecord.getHospital())
                    .cost(vaccinationRecord.getCost())
                    .vaccinationRound(vaccinationRecord.getVaccinationRound())
                    .imageUrl(vaccinationRecord.getImageUrl())
                    .build());
        }

        return recordResponseDtos;
    }
}
