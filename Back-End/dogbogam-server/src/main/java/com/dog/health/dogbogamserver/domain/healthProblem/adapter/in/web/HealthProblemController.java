package com.dog.health.dogbogamserver.domain.healthProblem.adapter.in.web;

import com.dog.health.dogbogamserver.domain.healthProblem.application.port.in.CreateHealthProblemUseCase;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health-problems")
@RequiredArgsConstructor
public class HealthProblemController {

    private final CreateHealthProblemUseCase createHealthProblemUseCase;

    // 반려견 건강고민 등록
    @PostMapping
    public SuccessResponse<?> createHealthProblem(@RequestParam Long dogId, @RequestParam String problem) {
        createHealthProblemUseCase.createHealthProblems(dogId, problem);
        return SuccessResponse.created();
    }


}
