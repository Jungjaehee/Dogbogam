package com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.in.*;

import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.port.out.*;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.CreateAiDiagnosisRequestDto;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.request.DiagnosisRequestDto;
import com.dog.health.dogbogamserver.domain.aiDiagnosis.application.service.dto.response.DiagnosisResultResponseDto;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.port.out.CreateAiReportDiseasePort;
import com.dog.health.dogbogamserver.domain.aiReportDisease.application.service.dto.request.CreateAiReportDiseaseDto;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogDetailsPort;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.global.aws.service.AwsService;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiDiagnosisService implements RequestSkinDiagnosisUseCase, RequestEyeDiagnosisUseCase,
        RequestObesityDiagnosisUseCase, RequestBreedDiagnosisUseCase {

    private final FindDogDetailsPort findDogDetailsPort;
    private final RequestSkinDiagnosisPort requestSkinDiagnosisPort;
    private final RequestEyeDiagnosisPort requestEyeDiagnosisPort;
    private final RequestBreedDiagnosisPort requestBreedDiagnosisPort;
    private final RequestObesityDiagnosisPort requestObesityDiagnosisPort;
    private final CreateAiDiagnosisPort createAiDiagnosisPort;
    private final CreateAiReportDiseasePort createAiReportDiseasePort;
    private final AwsService awsService;

    @Override
    public Map<String, Long> requestSkinDiagnosis(Long memberId, DiagnosisRequestDto requestDto) throws IOException {
        checkAccess(memberId, requestDto.getDogId());

        Map<String, Object> imageInfo = saveImage(requestDto, "skin");

        // 4. AI 예측
        DiagnosisResultResponseDto responseDto = requestSkinDiagnosisPort.requestSkinDiagnosis(requestDto.getImage());

        return saveResult(memberId, requestDto.getDogId(), imageInfo, "피부", responseDto);
    }

    @Override
    public Map<String, Long> requestEyeDiagnosis(Long memberId, DiagnosisRequestDto requestDto) throws IOException {
        checkAccess(memberId, requestDto.getDogId());

        Map<String, Object> imageInfo =  saveImage(requestDto, "eye");

        // 4. AI 예측
        DiagnosisResultResponseDto responseDto = requestEyeDiagnosisPort.requestEyeDiagnosis(requestDto.getImage());

        return saveResult(memberId, requestDto.getDogId(), imageInfo, "눈", responseDto);
    }

    @Override
    public Map<String, Long> requestObesityDiagnosis(Long memberId, DiagnosisRequestDto requestDto) throws IOException {
        checkAccess(memberId, requestDto.getDogId());

        Map<String, Object> imageInfo =  saveImage(requestDto, "obesity");

        // 4. AI 예측
        DiagnosisResultResponseDto responseDto = requestObesityDiagnosisPort.requestObesityDiagnosis(requestDto.getImage());

        return saveResult(memberId, requestDto.getDogId(), imageInfo, "비만", responseDto);
    }

    @Override
    public Map<String, Long> requestBreedDiagnosis(Long memberId, DiagnosisRequestDto requestDto) throws IOException {
        checkAccess(memberId, requestDto.getDogId());

        Map<String, Object> imageInfo =  saveImage(requestDto, "breed");

        // 4. AI 예측
        DiagnosisResultResponseDto responseDto = requestBreedDiagnosisPort.requestBreedDiagnosis(requestDto.getImage());

        return saveResult(memberId, requestDto.getDogId(), imageInfo, "견종", responseDto);
    }

    private void checkAccess(Long memberId, Long dogId){
        // 1. 해당 펫이 존재하는지 검사
        Dog dog = findDogDetailsPort.findByDogId(dogId)
                .orElseThrow(() -> new CustomException(ErrorCode.DOG_NOT_FOUND));

        // 2. 해당 반려견의 보호자가 로그인한 멤버인지 확인
        if(dog.getMember().getMemberId() != memberId)
            throw new CustomException(ErrorCode.DOG_NO_ACCESS);
    }
    private Map<String, Object> saveImage(DiagnosisRequestDto requestDto, String path) throws IOException {
        // 3. 사진 저장
        return awsService.uploadFile(requestDto.getImage(), path);
    }

    private Map<String, Long> saveResult(Long memberId, Long dogId, Map<String, Object> image,
                                            String path, DiagnosisResultResponseDto responseDto){
        // 5. 결과 저장을 위한 create dto 생성
        CreateAiDiagnosisRequestDto createRequestDto = new CreateAiDiagnosisRequestDto(
                dogId, responseDto.getData().get(0).getLabel().equals("정상"),
                path, image.get("s3FileName").toString(), image.get("uploadImageUrl").toString()
        );

        // 6. ai diagnosis DB 저장
        Long aiDiagnosisId = createAiDiagnosisPort.createAiDiagnosis(memberId, createRequestDto);

        // 7. ai diagnosis disease DB 저장
        for(int i=0; i<3; i++){
            createAiReportDiseasePort.createAiReportDisease(new CreateAiReportDiseaseDto(
                    aiDiagnosisId, responseDto.getData().get(i).getLabel(),
                    responseDto.getData().get(i).getConf()
            ));
        }

        Map<String, Long> aiDiagnosisResult = new HashMap<>();
        aiDiagnosisResult.put("aiDiagnosisId", aiDiagnosisId);
        return aiDiagnosisResult;
    }

}
