package com.dog.health.dogbogamserver.global.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 멤버
    USER_NOT_FOUND("존재하지 않는 유저입니다.", HttpStatus.UNAUTHORIZED),
    USER_VALIDATION_ERROR("유저 검증 오류가 발생했습니다.", HttpStatus.UNAUTHORIZED),
    INVALID_PASSWORD("올바르지 않은 비밀번호입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS_TOKEN("올바르지 않은 ACCESSTOKEN 입니다", HttpStatus.UNAUTHORIZED),
    JSON_PROCESSING_ERROR("JSON 처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    // 반려견
    DOG_NOT_FOUND("존재하지 않는 반려견입니다.", HttpStatus.NOT_FOUND),
    DOG_LIST_NOT_FOUND("해당 유저는 반려견이 없습니다.", HttpStatus.NOT_FOUND),
    DOG_NO_ACCESS("해당 반려견에 대한 접근 권한이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    // 보험
    INSURANCE_NOT_FOUND("존재하지 않는 보험입니다.", HttpStatus.NOT_FOUND),
    INSURANCE_RECORD_NOT_FOUND("존재하지 않는 보험 기록입니다.",HttpStatus.FOUND),
    INSURANCE_RECORD_ALREADY_EXIST("이미 등록된 보험입니다.", HttpStatus.CONFLICT),
    INSURANCE_RECORD_NO_ACCESS("해당 보험 기록에 대한 접근 권한이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    INSURANCE_RECOMMEND_NOT_FOUND("추천 보험을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INSURANCE_BENEFIT_NOT_FOUND("존재하지 않는 보장 혜택입니다.", HttpStatus.NOT_FOUND),

    HEALTH_PROBLEM_LIMIT_EXCEEDED("반려견의 건강 고민은 최대 3개까지만 등록할 수 있습니다.", HttpStatus.BAD_REQUEST),
    INVALID_HEALTH_PROBLEM("등록할 수 없는 건강 고민입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_HEALTH_PROBLEM("이미 등록된 건강 고민입니다.", HttpStatus.CONFLICT),
    HEALTH_PROBLEM_NOT_FOUND("존재하지 않는 건강 고민입니다.", HttpStatus.NOT_FOUND);

    // AI 진단
    AI_DIAGNOSIS_NOT_FOUND("해당 AI 예측 기록이 없습니다.", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
