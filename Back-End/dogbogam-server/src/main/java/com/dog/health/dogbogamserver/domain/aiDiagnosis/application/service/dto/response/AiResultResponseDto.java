package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AiResultResponseDto {

    private String label;

    private Float conf;
}
