package com.dog.health.dogbogamserver.domain.healthProblem.application.service;

import com.dog.health.dogbogamserver.domain.healthProblem.application.port.in.CreateHealthProblemUseCase;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.out.LoadHealthProblemPort;
import com.dog.health.dogbogamserver.domain.healthProblem.application.port.out.SaveHealthProblemPort;
import com.dog.health.dogbogamserver.domain.healthProblem.domain.HealthProblem;
import com.dog.health.dogbogamserver.domain.healthProblem.domain.ProblemType;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthProblemService implements CreateHealthProblemUseCase {

    private final SaveHealthProblemPort saveHealthProblemPort;
    private final LoadHealthProblemPort loadHealthProblemPort;

    @Override
    public void createHealthProblems(Long dogId, String problems) {
        // 입력받은 문제들을 쉼표로 구분
        List<String> problemList = Arrays.asList(problems.split("\\s*,\\s*"));

        // 유효성 검사: 문제의 개수 확인
        if (problemList.size() > 3) {
            throw new CustomException(ErrorCode.HEALTH_PROBLEM_LIMIT_EXCEEDED);
        }

        // 해당 반려견의 기존 건강 고민 조회
        List<HealthProblem> existingProblems = loadHealthProblemPort.loadHealthProblemsByDogId(dogId);

        // 1. 기존 문제 + 새로운 문제의 총 개수가 3개를 넘는지 확인
        if (existingProblems.size() + problemList.size() > 3) {
            throw new CustomException(ErrorCode.HEALTH_PROBLEM_LIMIT_EXCEEDED);
        }

        for (String problem : problemList) {
            // 2. Enum으로 유효성 검사
            if (!ProblemType.isValid(problem)) {
                throw new CustomException(ErrorCode.INVALID_HEALTH_PROBLEM);
            }

            // 3. 이미 등록된 건강 고민인지 확인
            if (existingProblems.stream().anyMatch(p -> p.getProblem().equals(problem))) {
                throw new CustomException(ErrorCode.DUPLICATE_HEALTH_PROBLEM);
            }

            // 4. 각 건강 고민 저장
            HealthProblem healthProblem = HealthProblem.builder()
                    .dog(loadHealthProblemPort.loadDogById(dogId)
                            .orElseThrow(() -> new CustomException(ErrorCode.DOG_NOT_FOUND)))
                    .problem(problem)
                    .build();

            saveHealthProblemPort.saveHealthProblem(healthProblem);
        }
    }
}
