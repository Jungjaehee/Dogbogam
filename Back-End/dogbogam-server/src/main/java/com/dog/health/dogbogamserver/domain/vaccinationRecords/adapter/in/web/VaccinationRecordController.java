package com.dog.health.dogbogamserver.domain.vaccinationRecords.adapter.in.web;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.in.*;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.CreateVaccinationRecordRequestDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.service.dto.request.UpdateVaccinationRecordRequestDto;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(summary = "특정 접종 기록 조회", description = "ID로 특정 백신 접종 기록을 조회합니다.")
    @GetMapping("/{recordId}")
    public SuccessResponse<VaccinationRecord> getVaccinationRecordById(@PathVariable("recordId") Long recordId) {
        return SuccessResponse.ok(findVaccinationRecordUseCase.findVaccinationRecordById(recordId));
    }

    @Operation(summary = "접종 기록 생성", description = "새로운 백신 접종 기록을 생성합니다.")
    @PostMapping
    public SuccessResponse<?> createVaccinationRecord(@ModelAttribute @Valid CreateVaccinationRecordRequestDto createVaccinationRecordRequestDto) throws IOException {
        log.info("Controller Create Vaccination record: {}", createVaccinationRecordRequestDto);
        createVaccinationRecordUseCase.createVaccinationRecord(createVaccinationRecordRequestDto);
        return SuccessResponse.created();
    }

    @Operation(summary = "접종 기록 업데이트", description = "기존 백신 접종 기록을 업데이트합니다.")
    @PatchMapping
    public SuccessResponse<?> updateVaccinationRecord(@ModelAttribute @Valid UpdateVaccinationRecordRequestDto updateVaccinationRecordRequestDto) throws IOException {
        log.info("Controller Update Vaccination record: {}", updateVaccinationRecordRequestDto);
        updateVaccinationRecordUseCase.updateVaccinationRecord(updateVaccinationRecordRequestDto);
        return SuccessResponse.updated();
    }

    @Operation(summary = "접종 기록 삭제", description = "특정 백신 접종 기록을 삭제합니다.")
    @DeleteMapping("/{recordId}")
    public SuccessResponse<?> deleteVaccinationRecord(@PathVariable("recordId") Long recordId) {
        log.info("Controller Delete Vaccination record: {}", recordId);
        deleteVaccinationRecordUseCase.deleteVaccinationRecord(recordId);
        return SuccessResponse.deleted();
    }

    @Operation(summary = "반려견 접종 기록 조회", description = "특정 반려견의 모든 백신 접종 기록을 조회합니다.")
    @GetMapping("/dog/{dogId}")
    public SuccessResponse<List<VaccinationRecord>> getAllVaccinationRecordsForDog(@PathVariable("dogId") Long dogId) {
        return SuccessResponse.ok(findVaccinationRecordsUseCase.findVaccinationsByDogId(dogId));
    }
}
