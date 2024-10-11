package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.web;

import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.RegistInsuranceRecordRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.UpdateInsuranceRecordRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in.*;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation; // 추가
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Insurance Record API", description = "마이펫보험 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("insurance-records")
public class InsuranceRecordController {

    private final RegistInsuranceRecordUseCase registInsuranceRecordUseCase;
    private final UpdateInsuranceRecordUseCase updateInsuranceRecordUseCase;
    private final FindInsuranceRecordUseCase findInsuranceRecordUseCase;
    private final DeleteInsuranceRecordUseCase deleteInsuranceRecordUseCase;
    private final FindAllInsuranceRecordUseCase findAllInsuranceRecordUseCase;

    @Operation(summary = "보험 기록 등록", description = "새로운 보험 기록을 등록합니다.")
    @PostMapping
    public SuccessResponse<?> saveInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                  @Valid @RequestBody final RegistInsuranceRecordRequestDto registRequestDto) {
        registInsuranceRecordUseCase.registInsuranceRecord(memberPrincipal.getMemberId(), registRequestDto);
        return SuccessResponse.created();
    }

    @Operation(summary = "보험 기록 업데이트", description = "기존 보험 기록을 업데이트합니다.")
    @PatchMapping
    public SuccessResponse<?> updateInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                    @Valid @RequestBody final UpdateInsuranceRecordRequestDto updateRequestDto) {
        updateInsuranceRecordUseCase.updateInsuranceRecord(memberPrincipal.getMemberId(), updateRequestDto);
        return SuccessResponse.updated();
    }

    @Operation(summary = "보험 기록 조회", description = "특정 보험 기록을 조회합니다.")
    @GetMapping("/{insuranceRecordId}")
    public SuccessResponse<?> findInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                  @PathVariable("insuranceRecordId") Long insuranceRecordId) {
        return SuccessResponse.ok(findInsuranceRecordUseCase.findInsuranceRecordById(memberPrincipal.getMemberId(), insuranceRecordId));
    }

    @Operation(summary = "보험 기록 삭제", description = "특정 보험 기록을 삭제합니다.")
    @DeleteMapping("/{insuranceRecordId}")
    public SuccessResponse<?> deleteInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                    @PathVariable("insuranceRecordId") Long insuranceRecordId) {
        deleteInsuranceRecordUseCase.deleteInsuranceRecord(memberPrincipal.getMemberId(), insuranceRecordId);
        return SuccessResponse.deleted();
    }

    @Operation(summary = "모든 보험 기록 조회", description = "특정 반려견에 대한 모든 보험 기록을 조회합니다.")
    @GetMapping("all/{dogId}")
    public SuccessResponse<?> findAllInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                     @PathVariable("dogId") Long dogId,
                                                     @RequestParam(value = "size", defaultValue = "5") int size,
                                                     @RequestParam(value = "page", defaultValue = "1") int page) {
        return SuccessResponse.ok(findAllInsuranceRecordUseCase.findAllInsuranceRecord(memberPrincipal.getMemberId(), dogId, size, page));
    }
}