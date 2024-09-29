package com.dog.health.dogbogamserver.domain.healthProblem.adapter.in.web;

import com.dog.health.dogbogamserver.domain.healthProblem.application.port.in.CreateHealthProblemUseCase;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.in.DeleteHealthProblemUseCase;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.in.FindHealthProblemsUseCase;
import com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.request.HealthProblemRequest;
import com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.response.HealthProblemResponse;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health-problems")
@RequiredArgsConstructor
public class HealthProblemController {

    private final CreateHealthProblemUseCase createHealthProblemUseCase;
    private final FindHealthProblemsUseCase findHealthProblemsUseCase;
    private final DeleteHealthProblemUseCase deleteHealthProblemUseCase;

    // 반려견 건강고민 등록
    @PostMapping
    public SuccessResponse<?> createHealthProblem(@AuthenticationPrincipal MemberPrincipal memberPrincipal, @Valid @RequestBody HealthProblemRequest request) {
        createHealthProblemUseCase.createHealthProblems(request.getDogId(), request.getProblem());
        return SuccessResponse.created();
    }

    // 반려견의 건강고민들 조회
    @GetMapping("/{dogId}")
    public SuccessResponse<List<HealthProblemResponse>> findHealthProblems(@AuthenticationPrincipal MemberPrincipal memberPrincipal, @PathVariable Long dogId) {
        List<HealthProblemResponse> healthProblems = findHealthProblemsUseCase.findHealthProblems(dogId);
        return SuccessResponse.ok(healthProblems);
    }

    // healthProblem 삭제
    @DeleteMapping("/{healthProblemId}")
    public SuccessResponse<?> deleteHealthProblem(@AuthenticationPrincipal MemberPrincipal memberPrincipal, @PathVariable Long healthProblemId) {
        deleteHealthProblemUseCase.deleteHealthProblem(healthProblemId);
        return SuccessResponse.ok();
    }


}
