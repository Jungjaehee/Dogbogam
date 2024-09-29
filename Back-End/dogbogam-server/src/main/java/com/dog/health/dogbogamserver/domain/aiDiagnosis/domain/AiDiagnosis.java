package com.dog.health.dogbogamserver.domain.aiDiagnosis.domain;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AiDiagnosis {
    private Long aiDiagnosisId;
    private Dog dog;
    private Boolean normal;
    private String diagnosisItem;
    private String imageName;
    private String imageUrl;
}
