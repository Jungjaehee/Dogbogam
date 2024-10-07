package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DiagnosisResultResponseDto {

    private Integer code;

    private String message;

    private List<AiResultResponseDto> data;

}
