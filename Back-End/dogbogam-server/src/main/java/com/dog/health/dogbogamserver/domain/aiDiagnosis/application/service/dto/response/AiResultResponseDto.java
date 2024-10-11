package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AiResultResponseDto {

    private String label;

    private Float conf;
}
