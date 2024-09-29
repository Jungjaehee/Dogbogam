package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CreateAiDiagnosisRequestDto {
    @NotEmpty(message = "강아지는 필수입니다.")
    private Dog dog;

    @NotEmpty(message = "정상 상태 여부는 필수입니다.")
    private Boolean normal;

    @NotBlank(message = "진단 항목은 필수입니다.")
    private String diagnosisItem;
    private String imageName;
    private String imageUrl;
}
