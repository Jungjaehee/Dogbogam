package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.DiagnosisResultResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface RequestObesityDiagnosisPort {

    DiagnosisResultResponseDto requestObesityDiagnosis(MultipartFile image);

}
