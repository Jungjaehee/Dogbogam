package com.dog.health.dogbogamserver.domain.vaccinationRecords.adapter.in.web;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in.*;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.CreateVaccinationRecordRequestDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.UpdateVaccinationRecordRequestDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/vaccination-records")
public class VaccinationRecordController {

    private final CreateVaccinationRecordUseCase createVaccinationRecordUseCase;
    private final UpdateVaccinationRecordUseCase updateVaccinationRecordUseCase;
    private final FindVaccinationRecordUseCase findVaccinationRecordUseCase;
    private final DeleteVaccinationRecordUseCase deleteVaccinationRecordUseCase;
    private final FindVaccinationRecordsUseCase findVaccinationRecordsUseCase;

    @GetMapping("/{recordId}")
    public SuccessResponse<VaccinationRecord> getVaccinationRecordById(@PathVariable("recordId") Long recordId) {
        return SuccessResponse.ok(findVaccinationRecordUseCase.findVaccinationRecordById(recordId));
    }

    @PostMapping
    public SuccessResponse<?> createVaccinationRecord(@RequestBody CreateVaccinationRecordRequestDto createVaccinationRecordRequestDto) {
        log.info("Controller Create Vaccination record: {}", createVaccinationRecordRequestDto);
        createVaccinationRecordUseCase.createVaccinationRecord(createVaccinationRecordRequestDto);
        return SuccessResponse.created();
    }

    @PatchMapping
    public SuccessResponse<?> updateVaccinationRecord(@RequestBody UpdateVaccinationRecordRequestDto updateVaccinationRecordRequestDto) {
        log.info("Controller Update Vaccination record: {}", updateVaccinationRecordRequestDto);
        updateVaccinationRecordUseCase.updateVaccinationRecord(updateVaccinationRecordRequestDto);
        return SuccessResponse.updated();
    }

    @DeleteMapping("/{recordId}")
    public SuccessResponse<?> deleteVaccinationRecord(@PathVariable("recordId") Long recordId) {
        log.info("Controller Delete Vaccination record: {}", recordId);
        deleteVaccinationRecordUseCase.deleteVaccinationRecord(recordId);
        return SuccessResponse.deleted();
    }

    @GetMapping("/dog/{dogId}")
    public SuccessResponse<List<VaccinationRecord>> getAllVaccinationRecordsForDog(@PathVariable("dogId") Long dogId) {
        return SuccessResponse.ok(findVaccinationRecordsUseCase.findVaccinationsByDogId(dogId));
    }
}
