package com.dog.health.dogbogamserver.global.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("존재하지 않는 유저입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_PASSWORD("올바르지 않은 비밀번호입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS_TOKEN("올바르지 않은 ACCESSTOKEN 입니다", HttpStatus.UNAUTHORIZED),
    JSON_PROCESSING_ERROR("JSON 처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    NOT_FOUND_DOG("해당 반려견을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NO_ACCESS_DOG("해당 반려견에 대한 접근 권한이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    NOT_FOUND_INSURANCE("해당 보험을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_INSURANCE_RECORD("해당 보험 기록을 찾을 수 없습니다.",HttpStatus.FOUND),
    ALREADY_EXIST_INSURANCE_RECORD("이미 등록된 보험입니다.", HttpStatus.CONFLICT),
    NO_ACCESS_INSURANCE_RECORD("해당 보험 기록에 대한 접근 권한이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED);


    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
