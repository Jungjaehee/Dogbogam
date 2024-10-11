package com.dog.health.dogbogamserver.domain.medicalRecords.application.service;

import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogDetailsPort;
import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence.MedicalRecordEntity;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.*;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out.*;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateMedicalReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateMedicalReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.response.FindMedicalReportResponseDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import com.dog.health.dogbogamserver.global.aws.service.AwsService;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
    public void createReport(CreateMedicalReportRequestDto createMedicalReportRequestDto) throws IOException {
        log.info("Service Create record : {}", createMedicalReportRequestDto);
        Map<String, Object> uploadParam = awsService.uploadFile(createMedicalReportRequestDto.getImage(), path);

        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder()
                .recordTime(createMedicalReportRequestDto.getRecordTime())
                .dog(dogDetailsPort.findEntityByDogId(createMedicalReportRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견 입니다.")))
                .imageName(uploadParam.get("s3FileName").toString())
                .imageUrl(uploadParam.get("uploadImageUrl").toString())
                .content(createMedicalReportRequestDto.getContent())
                .hospital(createMedicalReportRequestDto.getHospital())
                .cost(createMedicalReportRequestDto.getCost())
                .build();
        createReportPort.createReport(medicalRecordEntity);
    }

    @Override
    public void updateReport(UpdateMedicalReportRequestDto updateMedicalReportRequestDto) throws IOException {
        log.info("Service Update record : {}", updateMedicalReportRequestDto);
        Map<String, Object> uploadParam = awsService.uploadFile(updateMedicalReportRequestDto.getImage(), path);
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder()
                .medicalRecordId(updateMedicalReportRequestDto.getMedicalRecordId())
                .recordTime(updateMedicalReportRequestDto.getRecordTime())
                .dog(dogDetailsPort.findEntityByDogId(updateMedicalReportRequestDto.getDogId())
                        .orElseThrow(()-> new IllegalArgumentException("없는 반려견입니다.")))
                .imageName(uploadParam.get("s3FileName").toString())
                .imageUrl(uploadParam.get("uploadImageUrl").toString())
                .content(updateMedicalReportRequestDto.getContent())
                .hospital(updateMedicalReportRequestDto.getHospital())
                .cost(updateMedicalReportRequestDto.getCost())
                .build();
        updateReportPort.updateReport(medicalRecordEntity);
    }

    @Override
    public FindMedicalReportResponseDto findMedicalRecordById(Long recordId) {
        log.info("Service Find record : {}", recordId);
        MedicalRecord medicalRecord = findReportPort.findMedicalRecordById(recordId)
                .orElseThrow(()-> new IllegalArgumentException("없는 리포트입니다."));

        return FindMedicalReportResponseDto.builder()
                .medicalRecordId(medicalRecord.getMedicalRecordId())
                .recordTime(medicalRecord.getRecordTime())
                .content(medicalRecord.getContent())
                .hospital(medicalRecord.getHospital())
                .cost(medicalRecord.getCost())
                .imageUrl(medicalRecord.getImageUrl())
                .build();
    }

    @Override
    public void deleteReportUseCase(Long reportId) throws IOException {
        log.info("Service Delete record : {}", reportId);
        MedicalRecord entity = findReportPort.findMedicalRecordById(reportId)
                .orElseThrow(()-> new CustomException(ErrorCode.MEDICAL_RECORD_NOT_FOUND));
        awsService.deleteFile(entity.getImageName());
        deleteReportPort.deleteReport(reportId);
    }

    @Override
    public List<FindMedicalReportResponseDto> findReportsByDogId(Long dogId) {
        log.info("Service Find records : {}", dogId);
        List<MedicalRecord> medicalRecords = findReportsPort.findReportsByDogId(dogId);

        List<FindMedicalReportResponseDto> findMedicalReportResponseDtos = new ArrayList<>();
        for(MedicalRecord medicalRecord : medicalRecords) {
            findMedicalReportResponseDtos.add(FindMedicalReportResponseDto.builder()
                            .medicalRecordId(medicalRecord.getMedicalRecordId())
                            .recordTime(medicalRecord.getRecordTime())
                            .content(medicalRecord.getContent())
                            .hospital(medicalRecord.getHospital())
                            .cost(medicalRecord.getCost())
                            .imageUrl(medicalRecord.getImageUrl())
                            .build());
        }

        return findMedicalReportResponseDtos;
    }
}
