package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.in.web;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.RequestBreedDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.RequestEyeDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.RequestObesityDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.RequestSkinDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.DiagnosisRequestDto;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/ai-diagnosis")
@RequiredArgsConstructor
@Slf4j
public class AiDiagnosisController {

    private final RequestSkinDiagnosisUseCase requestSkinDiagnosisUseCase;
    private final RequestEyeDiagnosisUseCase requestEyeDiagnosisUseCase;
    private final RequestObesityDiagnosisUseCase requestObesityDiagnosisUseCase;
    private final RequestBreedDiagnosisUseCase requestBreedDiagnosisUseCase;

    @PostMapping(value = "/eye", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SuccessResponse<Map<String, Long>> eyeDiagnosis(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                              @ModelAttribute @Valid DiagnosisRequestDto requestDto) throws IOException {
        return SuccessResponse.created(requestEyeDiagnosisUseCase.requestEyeDiagnosis(memberPrincipal.getMemberId()
                , requestDto));
    }

    @PostMapping(value="/skin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SuccessResponse<Map<String, Long>> skinDiagnosis(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                            @ModelAttribute @Valid DiagnosisRequestDto requestDto) throws IOException {

        return SuccessResponse.created(requestSkinDiagnosisUseCase.requestSkinDiagnosis(memberPrincipal.getMemberId()
                , requestDto));
    }

    @PostMapping(value="/obesity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SuccessResponse<Map<String, Long>> obesityDiagnosis(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                              @ModelAttribute @Valid DiagnosisRequestDto requestDto) throws IOException {

        return SuccessResponse.created(requestObesityDiagnosisUseCase.requestObesityDiagnosis(memberPrincipal.getMemberId()
                , requestDto));
    }

    @PostMapping(value="/breed", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SuccessResponse<Map<String, Long>> breedDiagnosis(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                              @ModelAttribute @Valid DiagnosisRequestDto requestDto) throws IOException {

        return SuccessResponse.created(requestBreedDiagnosisUseCase.requestBreedDiagnosis(memberPrincipal.getMemberId(),
                requestDto));
    }
}
