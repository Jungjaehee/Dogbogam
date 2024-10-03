package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.in.web;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.CreateAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.DeleteAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.FindAiDiagnosesUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.FindAiDiagnosisUseCase;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai-diagnosis")
@RequiredArgsConstructor
public class AiDiagnosisReportController {
    private final CreateAiDiagnosisUseCase createAiDiagnosisUseCase;
    private final FindAiDiagnosisUseCase findAiDiagnosisUseCase;
    private final FindAiDiagnosesUseCase findAiDiagnosesUseCase;
    private final DeleteAiDiagnosisUseCase deleteAiDiagnosisUseCase;

    @Operation(summary = "AI 진단 생성", description = "회원 ID와 진단 정보를 이용하여 AI 진단을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "진단 생성 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public SuccessResponse<?> createDiagnosis(
            @AuthenticationPrincipal @Parameter(description = "회원 정보", required = true) MemberPrincipal memberPrincipal,
            @Valid @RequestBody @Parameter(description = "AI 진단 생성 요청 DTO", required = true) CreateAiDiagnosisRequestDto createAiDiagnosisRequestDto) {
        createAiDiagnosisUseCase.createAiDiagnosis(memberPrincipal.getMemberId(), createAiDiagnosisRequestDto);
        return SuccessResponse.created();
    }

    @Operation(summary = "AI 진단 조회", description = "AI 진단 ID로 AI 진단 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "진단 조회 성공"),
            @ApiResponse(responseCode = "404", description = "진단을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/report/{aiDiagnosisId}")
    public SuccessResponse<?> findDiagnosisById(@Parameter(description = "AI 진단 ID", required = true) @PathVariable("aiDiagnosisId") Long aiDiagnosisId) {
        return SuccessResponse.ok(findAiDiagnosisUseCase.findAiDiagnosisByAiDiagnosisId(aiDiagnosisId));
    }

    @Operation(summary = "AI 진단 목록 조회", description = "강아지 ID로 AI 진단 목록을 페이지네이션 하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "진단 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "진단 목록을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/report/list/{dogId}")
    public SuccessResponse<?> findDiagnosesById(
            @Parameter(description = "강아지 ID", required = true) @PathVariable("dogId") Long dogId,
            @RequestParam(value = "page", defaultValue = "1") @Parameter(description = "페이지 번호", example = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") @Parameter(description = "페이지 크기", example = "10") int size) {
        return SuccessResponse.ok(findAiDiagnosesUseCase.findAiDiagnosesByDogId(dogId, page, size));
    }

    @Operation(summary = "AI 진단 삭제", description = "AI 진단 ID로 AI 진단 정보를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "진단 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "진단을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/report/{aiDiagnosisId}")
    public SuccessResponse<?> deleteDiagnosisById(@Parameter(description = "AI 진단 ID", required = true) @PathVariable("aiDiagnosisId") Long aiDiagnosisId) {
        deleteAiDiagnosisUseCase.deleteAiDiagnosis(aiDiagnosisId);
        return SuccessResponse.deleted();
    }
}
