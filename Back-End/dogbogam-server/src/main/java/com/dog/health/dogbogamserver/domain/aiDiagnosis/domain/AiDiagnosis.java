package com.dog.health.dogbogamserver.domain.aiDiagnosis.domain;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class AiDiagnosis {
    private Long aiDiagnosisId;
    private Long dogId;
    private Boolean normal;
    private String diagnosisItem;
    private String imageName;
    private String imageUrl;
    private LocalDateTime createdAt;
}
