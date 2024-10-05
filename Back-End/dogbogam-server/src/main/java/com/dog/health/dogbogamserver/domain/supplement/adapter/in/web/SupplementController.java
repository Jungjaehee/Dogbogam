package com.dog.health.dogbogamserver.domain.supplement.adapter.in.web;

import com.dog.health.dogbogamserver.domain.supplement.application.port.in.FindSupplementsUseCase;
import com.dog.health.dogbogamserver.domain.supplement.application.service.dto.response.SupplementSummaryResponse;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/supplements")
@RequiredArgsConstructor
public class SupplementController {

    private final FindSupplementsUseCase findSupplementsUseCase;

    // 모든 영양제 조회
    @GetMapping("/all")
    public SuccessResponse<List<SupplementSummaryResponse>> getAllSupplements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<SupplementSummaryResponse> supplements = findSupplementsUseCase.findAllSupplements(page, size);
        return SuccessResponse.ok(supplements);
    }
}
