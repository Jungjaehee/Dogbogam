package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.web;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.*;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    private final CreateReportUseCase createReportUseCase;
    private final UpdateReportUseCase updateReportUseCase;
    private final FindReportUseCase findReportUseCase;
    private final DeleteReportUseCase deleteReportUseCase;
    private final FindReportsUseCase findReportsUseCase;

    @GetMapping("/{recordId}")
    public SuccessResponse<MedicalRecord> getMedicalRecordById(@PathVariable("recordId") Long recordId) {
        return SuccessResponse.ok(findReportUseCase.findMedicalRecordById(recordId));
    }

    @PostMapping
    public SuccessResponse<?> createMedicalRecord(@RequestBody CreateReportRequestDto createReportRequestDto) {
        log.info("Controller Create medical record: {}", createReportRequestDto);
        createReportUseCase.createReport(createReportRequestDto);
        return SuccessResponse.created();
    }

    @PatchMapping
    public SuccessResponse<?> updateMedicalRecord(@RequestBody UpdateReportRequestDto updateReportRequestDto) {
        log.info("Controller Update medical record: {}", updateReportRequestDto);
        updateReportUseCase.updateReport(updateReportRequestDto);
        return SuccessResponse.updated();
    }

    @DeleteMapping("/{recordId}")
    public SuccessResponse<?> deleteMedicalRecord(@PathVariable("recordId") Long recordId) {
        log.info("Controller Delete medical record: {}", recordId);
        deleteReportUseCase.deleteReportUseCase(recordId);
        return SuccessResponse.deleted();
    }

    @GetMapping("/dog/{dogId}")
    public SuccessResponse<List<MedicalRecord>> getAllMedicalRecordsForDog(@PathVariable("dogId") Long dogId) {
        return SuccessResponse.ok(findReportsUseCase.findReportsByDogId(dogId));
    }
}
