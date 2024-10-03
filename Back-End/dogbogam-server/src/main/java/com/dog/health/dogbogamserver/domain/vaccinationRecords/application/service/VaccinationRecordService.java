package com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service;


import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.adapter.out.persistence.VaccinationRecordEntity;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in.*;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.out.*;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.CreateVaccinationRecordRequestDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.UpdateVaccinationRecordRequestDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;
import com.dog.health.dogbogamserver.global.aws.service.AwsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    private static final String path = "vaccination_record_image";

    @Override
    public void createVaccinationRecord(CreateVaccinationRecordRequestDto createVaccinationRecordRequestDto) throws IOException {
        log.info("Service Create record : {}", createVaccinationRecordRequestDto);
        Map<String, String> uploadParam = awsService.uploadFile(createVaccinationRecordRequestDto.getImage(), path);
        VaccinationRecordEntity vaccinationRecordEntity = VaccinationRecordEntity.builder()
                .recordTime(createVaccinationRecordRequestDto.getRecordTime())
                .dog(dogPersistenceAdapter.findEntityByDogId(createVaccinationRecordRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견 입니다.")))
                .imageName(uploadParam.get("s3FileName"))
                .imageUrl(uploadParam.get("uploadImageUrl"))
                .content(createVaccinationRecordRequestDto.getContent())
                .hospital(createVaccinationRecordRequestDto.getHospital())
                .cost(createVaccinationRecordRequestDto.getCost())
                .vaccinationRound(createVaccinationRecordRequestDto.getVaccinationRound())
                .build();
        createVaccinationRecordPort.createVaccinationRecord(vaccinationRecordEntity);
    }

    @Override
    public void updateVaccinationRecord(UpdateVaccinationRecordRequestDto updateVaccinationRecordRequestDto) throws IOException {
        log.info("Service Update record : {}", updateVaccinationRecordRequestDto);
        Map<String, String> uploadParam = awsService.uploadFile(updateVaccinationRecordRequestDto.getImage(), path);
        VaccinationRecordEntity vaccinationRecordEntity = VaccinationRecordEntity.builder()
                .vaccinationRecordId(updateVaccinationRecordRequestDto.getVaccinationRecordId())
                .recordTime(updateVaccinationRecordRequestDto.getRecordTime())
                .dog(dogPersistenceAdapter.findEntityByDogId(updateVaccinationRecordRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견입니다.")))
                .imageName(uploadParam.get("s3FileName"))
                .imageUrl(uploadParam.get("uploadImageUrl"))
                .content(updateVaccinationRecordRequestDto.getContent())
                .hospital(updateVaccinationRecordRequestDto.getHospital())
                .cost(updateVaccinationRecordRequestDto.getCost())
                .vaccinationRound(updateVaccinationRecordRequestDto.getVaccinationRound())
                .build();
        updateVaccinationRecordPort.updateVaccinationRecord(vaccinationRecordEntity);
    }

    @Override
    public VaccinationRecord findVaccinationRecordById(Long vaccinationRecordId) {
        log.info("Service Find record : {}", vaccinationRecordId);
        return findVaccinationRecordPort.findVaccinationRecordById(vaccinationRecordId)
                .orElseThrow(()-> new IllegalArgumentException("없는 리포트입니다."));
    }

    @Override
    public void deleteVaccinationRecord(Long vaccinationRecordId) throws IOException {
        log.info("Service Delete record : {}", vaccinationRecordId);
        VaccinationRecord vaccinationRecord = findVaccinationRecordById(vaccinationRecordId);
        awsService.deleteFile(vaccinationRecord.getImageName());
        deleteVaccinationRecordPort.deleteVaccinationRecord(vaccinationRecordId);
    }

    @Override
    public List<VaccinationRecord> findVaccinationsByDogId(Long dogId) {
        log.info("Service Find records : {}", dogId);
        return findVaccinationRecordsPort.findVaccinationRecordsByDogId(dogId)
                .orElseThrow(()->new IllegalArgumentException("없는 접종 기록입니다."));
    }
}
