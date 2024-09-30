package com.dog.health.dogbogamserver.domain.insurance.adapter.in.web;

import com.dog.health.dogbogamserver.domain.insurance.adapter.in.dto.DiagnosisItem;
import com.dog.health.dogbogamserver.domain.insurance.application.port.in.*;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Insurance API", description = "보험 관련 API")
@RestController
@RequestMapping("/insurances")
@RequiredArgsConstructor
public class InsuranceController {

    private final SearchInsuranceUseCase searchInsuranceUseCase;
    private final FindAllInsuranceUseCase findAllInsuranceUseCase;
    private final FindAllInsuranceBenefitUseCase findAllInsuranceBenefitUseCase;
    private final FindDetailInsuranceUseCase findDetailInsuranceUseCase;
    private final RecommendInsuranceUseCase recommendInsuranceUseCase;

    @Operation(summary = "보험 검색", description = "보장 혜택으로 보험을 검색합니다.")
    @GetMapping("/search")
    public SuccessResponse<?> searchInsurance(@RequestParam("benefit") final List<String> benefit){
        return SuccessResponse.ok(searchInsuranceUseCase.search(benefit));
    }

    @Operation(summary = "보험 전체 리스트 조회", description = "보험 전체 리스트를 조회합니다.")
    @GetMapping
    public SuccessResponse<?> findAllInsurances(){
        return SuccessResponse.ok(findAllInsuranceUseCase.findAll());
    }

    @Operation(summary = "보장 혜택 전체 리스트 조회", description = "보험들의 보장 혜택들을 조회합니다.")
    @GetMapping("/benefit")
    public SuccessResponse<?> findAllInsuranceBenefits(){
        return SuccessResponse.ok(findAllInsuranceBenefitUseCase.findAllBenefits());
    }

    @Operation(summary = "보험 상세 조회", description = "보험 id로 보험 정보를 상세 조회합니다.")
    @GetMapping("/{insuranceId}")
    public SuccessResponse<?> findDetailInsuranceById(@PathVariable("insuranceId") final Long insuranceId){
        return SuccessResponse.ok(findDetailInsuranceUseCase.findDetailByInsuranceId(insuranceId));
    }

    @Operation(summary = "보험 추천", description = "AI 진단 항목을 바탕으로 보험을 추천합니다.")
    @GetMapping("/recommend")
    public SuccessResponse<?> recommendBydiagonosis(@RequestParam("diagnosisItem") final DiagnosisItem diagnosisItem){
        return SuccessResponse.ok(recommendInsuranceUseCase.recommendInsurance(diagnosisItem));
    }
}
