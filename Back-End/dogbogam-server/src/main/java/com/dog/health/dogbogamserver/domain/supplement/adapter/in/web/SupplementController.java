package com.dog.health.dogbogamserver.domain.supplement.adapter.in.web;

import com.dog.health.dogbogamserver.domain.supplement.application.port.in.FindSupplementUseCase;
import com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response.SupplementDetailResponse;
import com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response.SupplementSummaryResponse;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplements")
@RequiredArgsConstructor
public class SupplementController {

    private final FindSupplementUseCase findSupplementUseCase;

    // 모든 영양제 조회
    @GetMapping("/all")
    public SuccessResponse<List<SupplementSummaryResponse>> getAllSupplements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<SupplementSummaryResponse> supplements = findSupplementUseCase.findAllSupplements(page, size);
        return SuccessResponse.ok(supplements);
    }

    // 영양제 상세 조회
    @GetMapping("/{supplementId}")
    public SuccessResponse<SupplementDetailResponse> findSupplementById(@PathVariable Long supplementId) {
        SupplementDetailResponse supplement = findSupplementUseCase.findSupplementById(supplementId);
        return SuccessResponse.ok(supplement);
    }
}
