package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.domain.AiDiagnosis;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class FindAiDiagnosesResponseDto {
    private int size;
    private long totalElements;
    private int currantPage;
    private int totalPages;
    private List<FindAiDiagnosisResponseDto> diagnoses;
}
