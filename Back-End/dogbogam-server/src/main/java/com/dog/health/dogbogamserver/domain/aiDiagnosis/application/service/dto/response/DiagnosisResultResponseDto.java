package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DiagnosisResultResponseDto {

    private Integer code;

    private String message;

    private List<AiResultDto> data;

}
