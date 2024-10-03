package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.DiagnosisResultResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "aiDiagnosisClient", url = "${gpu.server.uri}")
public interface AiDiagnosisFeignClient {

    @PostMapping(value = "/eye", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    DiagnosisResultResponseDto requestEyeDiagnosis(@RequestPart("image") MultipartFile image);

    @PostMapping(value = "/skin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    DiagnosisResultResponseDto requestSkinDiagnosis(@RequestPart("image") MultipartFile image);

    @PostMapping(value = "/obesity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    DiagnosisResultResponseDto requestObesityDiagnosis(@RequestPart("image") MultipartFile image);

    @PostMapping(value = "/breed", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    DiagnosisResultResponseDto requestBreedDiagnosis(@RequestPart("image") MultipartFile image);
}