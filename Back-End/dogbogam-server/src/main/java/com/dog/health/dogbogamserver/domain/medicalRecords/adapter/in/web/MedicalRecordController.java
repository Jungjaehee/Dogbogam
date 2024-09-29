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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(summary = "병원 기록 ID로 조회", description = "병원 기록 ID를 이용하여 특정 병원 기록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "병원 기록 조회 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MedicalRecord.class))),
            @ApiResponse(responseCode = "404", description = "병원 기록을 찾을 수 없음", content = @Content)
    })
    @GetMapping("/{medicalRecordId}")
    public SuccessResponse<MedicalRecord> getMedicalRecordById(@PathVariable("medicalRecordId") Long medicalRecordId) {
        return SuccessResponse.ok(findReportUseCase.findMedicalRecordById(medicalRecordId));
    }

    @Operation(summary = "병원 기록 생성", description = "반려견의 새로운 병원 기록을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "병원 기록 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력", content = @Content)
    })
    @PostMapping
    public SuccessResponse<?> createMedicalRecord(@RequestBody CreateReportRequestDto createReportRequestDto) {
        log.info("Controller Create medical record: {}", createReportRequestDto);
        createReportUseCase.createReport(createReportRequestDto);
        return SuccessResponse.created();
    }

    @Operation(summary = "병원 기록 수정", description = "기존의 병원 기록을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "병원 기록 수정 성공"),
            @ApiResponse(responseCode = "404", description = "병원 기록을 찾을 수 없음", content = @Content)
    })
    @PatchMapping
    public SuccessResponse<?> updateMedicalRecord(@RequestBody UpdateReportRequestDto updateReportRequestDto) {
        log.info("Controller Update medical record: {}", updateReportRequestDto);
        updateReportUseCase.updateReport(updateReportRequestDto);
        return SuccessResponse.updated();
    }

    @Operation(summary = "병원 기록 삭제", description = "병원 기록 ID를 이용하여 특정 병원 기록을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "병원 기록 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "병원 기록을 찾을 수 없음", content = @Content)
    })
    @DeleteMapping("/{medicalRecordId}")
    public SuccessResponse<?> deleteMedicalRecord(@PathVariable("medicalRecordId") Long medicalRecordId) {
        log.info("Controller Delete medical record: {}", medicalRecordId);
        deleteReportUseCase.deleteReportUseCase(medicalRecordId);
        return SuccessResponse.deleted();
    }

    @Operation(summary = "반려견 ID로 병원 기록 조회", description = "반려견의 ID로 해당 반려견의 모든 병원 기록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "병원 기록 조회 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MedicalRecord.class))),
            @ApiResponse(responseCode = "404", description = "해당 반려견의 병원 기록을 찾을 수 없음", content = @Content)
    })
    @GetMapping("/dog/{dogId}")
    public SuccessResponse<List<MedicalRecord>> getAllMedicalRecordsForDog(@PathVariable("dogId") Long dogId) {
        return SuccessResponse.ok(findReportsUseCase.findReportsByDogId(dogId));
    }
}
