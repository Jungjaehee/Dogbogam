package com.dog.health.dogbogamserver.domain.healthProblem.application.service;

import com.dog.health.dogbogamserver.domain.healthProblem.application.port.in.CreateHealthProblemUseCase;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.in.DeleteHealthProblemUseCase;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.in.FindHealthProblemsUseCase;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.out.DeleteHealthProblemPort;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.out.LoadHealthProblemPort;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.out.SaveHealthProblemPort;
import com.dog.health.dogbogamserver.domain.healthProblem.application.service.dto.response.HealthProblemResponse;
import com.dog.health.dogbogamserver.domain.healthProblem.domain.HealthProblem;
import com.dog.health.dogbogamserver.domain.healthProblem.domain.ProblemType;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HealthProblemService implements CreateHealthProblemUseCase, FindHealthProblemsUseCase, DeleteHealthProblemUseCase {

    private final SaveHealthProblemPort saveHealthProblemPort;
    private final LoadHealthProblemPort loadHealthProblemPort;
    private final DeleteHealthProblemPort deleteHealthProblemPort;

    @Override
    public void createHealthProblems(Long dogId, List<String> problems) {
        // 유효성 검사: 문제의 개수 확인
        if (problems.size() > 3) {
            throw new CustomException(ErrorCode.HEALTH_PROBLEM_LIMIT_EXCEEDED);
        }

        // 해당 반려견의 기존 건강 고민 조회
        List<HealthProblem> existingProblems = loadHealthProblemPort.loadHealthProblemsByDogId(dogId);

        // 해당 반려견의 기존 건강 고민 삭제
        for(HealthProblem problem : existingProblems) {
            deleteHealthProblem(problem.getHealthProblemId());
        }

        for (String problem : problems) {
            // Enum으로 유효성 검사
            if (!ProblemType.isValid(problem)) {
                throw new CustomException(ErrorCode.INVALID_HEALTH_PROBLEM);
            }

            // 각 건강 고민 저장
            HealthProblem healthProblem = HealthProblem.builder()
                    .dog(loadHealthProblemPort.loadDogById(dogId)
                            .orElseThrow(() -> new CustomException(ErrorCode.DOG_NOT_FOUND)))
                    .problem(problem)
                    .build();

            saveHealthProblemPort.saveHealthProblem(healthProblem);
        }
    }

    // 반려견의 건강 고민 리스트 조회
    @Override
    public List<HealthProblemResponse> findHealthProblems(Long dogId) {
        List<HealthProblem> healthProblems = loadHealthProblemPort.loadHealthProblemsByDogId(dogId);

        // HealthProblem을 HealthProblemResponse로 변환
        return healthProblems.stream()
                .map(healthProblem -> HealthProblemResponse.builder()
                        .problem(healthProblem.getProblem())
                        .build())
                .collect(Collectors.toList());
    }

    // 건강 고민 삭제
    @Override
    public void deleteHealthProblem(Long healthProblemId) {
        // healthProblem 존재 여부 확인
        loadHealthProblemPort.loadHealthProblemById(healthProblemId)
                .orElseThrow(() -> new CustomException(ErrorCode.HEALTH_PROBLEM_NOT_FOUND));

        // healthProblem 삭제
        deleteHealthProblemPort.deleteHealthProblem(healthProblemId);
    }
}
