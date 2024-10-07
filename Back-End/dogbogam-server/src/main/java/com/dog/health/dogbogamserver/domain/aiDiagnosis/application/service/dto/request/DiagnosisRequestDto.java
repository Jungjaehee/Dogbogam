package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiagnosisRequestDto {

    @NotNull(message = "반려견 Id는 필수입니다.")
    Long dogId;

    @NotNull(message = "image는 필수입니다.")
    MultipartFile image;

}
