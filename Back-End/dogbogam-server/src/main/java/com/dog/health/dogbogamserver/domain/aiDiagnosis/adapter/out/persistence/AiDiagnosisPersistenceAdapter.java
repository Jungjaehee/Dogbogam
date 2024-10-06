package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.*;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.DiagnosisResultResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
@Slf4j
public class AiDiagnosisPersistenceAdapter implements RequestSkinDiagnosisPort, RequestEyeDiagnosisPort,
       RequestObesityDiagnosisPort, RequestBreedDiagnosisPort {

    private final AiDiagnosisFeignClient aiDiagnosisFeignClient;

    @Value("${gpu.server.uri}")
    private String url;

    @Override
    public DiagnosisResultResponseDto requestSkinDiagnosis(MultipartFile image){
        return aiDiagnosisFeignClient.requestSkinDiagnosis(image);
    }

    @Override
    public DiagnosisResultResponseDto requestEyeDiagnosis(MultipartFile image){
        return aiDiagnosisFeignClient.requestEyeDiagnosis(image);
    }

    @Override
    public DiagnosisResultResponseDto requestObesityDiagnosis(MultipartFile image, String breed, Double weight){
        return aiDiagnosisFeignClient.requestObesityDiagnosis(image, breed, weight);
    }

    @Override
    public DiagnosisResultResponseDto requestBreedDiagnosis(MultipartFile image){
        return aiDiagnosisFeignClient.requestBreedDiagnosis(image);
    }

}
