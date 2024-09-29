package com.dog.health.dogbogamserver.global.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("존재하지 않는 유저입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_PASSWORD("올바르지 않은 비밀번호입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS_TOKEN("올바르지 않은 ACCESSTOKEN 입니다", HttpStatus.UNAUTHORIZED),
    JSON_PROCESSING_ERROR("JSON 처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    DOG_NOT_FOUND("존재하지 않는 반려견입니다.", HttpStatus.NOT_FOUND),
    DOG_LIST_NOT_FOUND("해당 유저는 반려견이 없습니다.", HttpStatus.NOT_FOUND),
    USER_VALIDATION_ERROR("유저 검증 오류가 발생했습니다.", HttpStatus.UNAUTHORIZED),

    DOG_NO_ACCESS("해당 반려견에 대한 접근 권한이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    INSURANCE_NOT_FOUND("해당 보험을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INSURANCE_RECORD_NOT_FOUND("해당 보험 기록을 찾을 수 없습니다.",HttpStatus.FOUND),
    INSURANCE_RECORD_ALREADY_EXIST("이미 등록된 보험입니다.", HttpStatus.CONFLICT),
    INSURANCE_RECORD_NO_ACCESS("해당 보험 기록에 대한 접근 권한이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),

    AI_DIAGNOSIS_NOT_FOUND("해당 AI 예측 기록이 없습니다.", HttpStatus.NOT_FOUND);


    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
