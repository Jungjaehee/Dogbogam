package com.dog.health.dogbogamserver.domain.medicalRecords.application.service;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence.MedicalRecordEntity;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.*;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.*;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordService implements CreateReportUseCase, UpdateReportUseCase, FindReportUseCase,
        DeleteReportUseCase, FindReportsUseCase {
    private final CreateReportPort createReportPort;
    private final UpdateReportPort updateReportPort;
    private final FindReportPort findReportPort;
    private final DeleteReportPort deleteReportPort;
    private final FindReportsPort findReportsPort;
    private final DogPersistenceAdapter dogPersistenceAdapter;

    @Override
    public void createReport(CreateReportRequestDto createReportRequestDto) {
        log.info("Service Create record : {}", createReportRequestDto);
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder()
                .recordTime(createReportRequestDto.getRecordTime())
                .dog(dogPersistenceAdapter.findEntityByDogId(createReportRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견 입니다.")))
                .content(createReportRequestDto.getContent())
                .hospital(createReportRequestDto.getHospital())
                .build();
        createReportPort.createReport(medicalRecordEntity);
    }

    @Override
    public void updateReport(UpdateReportRequestDto updateReportRequestDto) {
        log.info("Service Update record : {}", updateReportRequestDto);
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder()
                .medicalRecordId(updateReportRequestDto.getReportId())
                .recordTime(updateReportRequestDto.getRecordTime())
                .dog(dogPersistenceAdapter.findEntityByDogId(updateReportRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견입니다.")))
                .content(updateReportRequestDto.getContent())
                .hospital(updateReportRequestDto.getHospital())
                .build();
        updateReportPort.updateReport(medicalRecordEntity);
    }

    @Override
    public MedicalRecord findMedicalRecordById(Long recordId) {
        log.info("Service Find record : {}", recordId);
        return findReportPort.findMedicalRecordById(recordId)
                .orElseThrow(()-> new IllegalArgumentException("없는 리포트입니다."));
    }

    @Override
    public void deleteReportUseCase(Long reportId) {
        log.info("Service Delete record : {}", reportId);
        deleteReportPort.deleteReport(reportId);
    }

    @Override
    public List<MedicalRecord> findReportsByDogId(Long dogId) {
        log.info("Service Find records : {}", dogId);
        return findReportsPort.findReportsByDogId(dogId)
                .orElseThrow(()->new IllegalArgumentException("없는 리포트입니다."));
    }
}
