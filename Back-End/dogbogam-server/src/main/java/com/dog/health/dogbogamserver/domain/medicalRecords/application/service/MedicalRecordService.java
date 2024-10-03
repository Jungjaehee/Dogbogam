package com.dog.health.dogbogamserver.domain.medicalRecords.application.service;

import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogDetailsPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence.MedicalRecordEntity;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.*;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.*;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
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
public class MedicalRecordService implements CreateReportUseCase, UpdateReportUseCase, FindReportUseCase,
        DeleteReportUseCase, FindReportsUseCase {
    private final CreateReportPort createReportPort;
    private final UpdateReportPort updateReportPort;
    private final FindReportPort findReportPort;
    private final DeleteReportPort deleteReportPort;
    private final FindReportsPort findReportsPort;
    private final FindDogDetailsPort dogDetailsPort;
    private final AwsService awsService;
    private static final String path = "medical_record_image";

    @Override
    public void createReport(CreateReportRequestDto createReportRequestDto) throws IOException {
        log.info("Service Create record : {}", createReportRequestDto);
        Map<String, String> uploadParam = awsService.uploadFile(createReportRequestDto.getImage(), path);

        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder()
                .recordTime(createReportRequestDto.getRecordTime())
                .dog(dogDetailsPort.findEntityByDogId(createReportRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견 입니다.")))
                .imageName(uploadParam.get("s3FileName"))
                .imageUrl(uploadParam.get("uploadImageUrl"))
                .content(createReportRequestDto.getContent())
                .hospital(createReportRequestDto.getHospital())
                .build();
        createReportPort.createReport(medicalRecordEntity);
    }

    @Override
    public void updateReport(UpdateReportRequestDto updateReportRequestDto) throws IOException {
        log.info("Service Update record : {}", updateReportRequestDto);
        Map<String, String> uploadParam = awsService.uploadFile(updateReportRequestDto.getImage(), path);
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder()
                .medicalRecordId(updateReportRequestDto.getMedicalRecordId())
                .recordTime(updateReportRequestDto.getRecordTime())
                .dog(dogDetailsPort.findEntityByDogId(updateReportRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견입니다.")))
                .imageName(uploadParam.get("s3FileName"))
                .imageUrl(uploadParam.get("uploadImageUrl"))
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
    public void deleteReportUseCase(Long reportId) throws IOException {
        log.info("Service Delete record : {}", reportId);
        MedicalRecord entity = findMedicalRecordById(reportId);
        awsService.deleteFile(entity.getImageName());
        deleteReportPort.deleteReport(reportId);
    }

    @Override
    public List<MedicalRecord> findReportsByDogId(Long dogId) {
        log.info("Service Find records : {}", dogId);
        return findReportsPort.findReportsByDogId(dogId)
                .orElseThrow(()->new IllegalArgumentException("없는 리포트입니다."));
    }
}
