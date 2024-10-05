package com.dog.health.dogbogamserver.domain.suppliments.adapter.in.web;

import com.dog.health.dogbogamserver.domain.suppliments.application.port.in.FindSupplementUseCase;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplements")
@RequiredArgsConstructor
public class SupplementController {

    private final FindSupplementUseCase findSupplementUseCase;

    // 모든 영양제 조회하기
    @GetMapping("/all")
    public SuccessResponse<?> getSupplements(@RequestParam int page, @RequestParam int size) {
        return SuccessResponse.ok(findSupplementUseCase.findAllSupplements(page, size));
    }
}
