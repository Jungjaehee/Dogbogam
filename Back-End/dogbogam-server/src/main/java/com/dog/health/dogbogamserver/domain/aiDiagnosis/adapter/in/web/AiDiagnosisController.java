package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.in.web;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.CreateAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.FindAiDiagnosesUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.FindAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai-diagnosis")
@RequiredArgsConstructor
public class AiDiagnosisController {
    private final CreateAiDiagnosisUseCase createAiDiagnosisUseCase;
    private final FindAiDiagnosisUseCase findAiDiagnosisUseCase;
    private final FindAiDiagnosesUseCase findAiDiagnosesUseCase;

    @PostMapping
    public SuccessResponse<?> createDiagnosis(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                              @Valid @RequestBody CreateAiDiagnosisRequestDto
                                              createAiDiagnosisRequestDto) {
        createAiDiagnosisUseCase.createAiDiagnosis(memberPrincipal.getMemberId(), createAiDiagnosisRequestDto);
        return SuccessResponse.created();
    }

    @GetMapping("/report/{aiDiagnosisId}")
    public SuccessResponse<?> findDiagnosisById(@PathVariable("aiDiagnosisId") Long aiDiagnosisId) {
        return SuccessResponse.created(findAiDiagnosisUseCase.findAiDiagnosisByAiDiagnosisId(aiDiagnosisId));
    }

    @GetMapping("/report/list/{dogId}")
    public SuccessResponse<?> findDiagnosesById(@PathVariable("dogId") Long dogId) {
        return SuccessResponse.created(findAiDiagnosesUseCase.findAiDiagnosesByDogId(dogId));
    }
}
