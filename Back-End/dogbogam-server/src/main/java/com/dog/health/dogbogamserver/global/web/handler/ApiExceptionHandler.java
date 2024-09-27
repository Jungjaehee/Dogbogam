package com.dog.health.dogbogamserver.global.web.handler;

import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.dto.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    // 커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException exception) {
        ExceptionResponse response = buildResponse(exception.getErrorCode().getHttpStatus(), exception.getMessage());
        return new ResponseEntity<>(response, exception.getErrorCode().getHttpStatus());
    }

    // 클라이언트의 잘못된 요청으로 발생하는 예외 처리
    @ExceptionHandler({
            IllegalArgumentException.class,
            ServletRequestBindingException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestPartException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            BindException.class
    })
    public ResponseEntity<ExceptionResponse> badRequestHandler(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    // 존재하지 않는 URL 또는 리소스를 요청할 때 발생하는 예외를 처리
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(NoHandlerFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    // 지원되지 않는 HTTP 메서드 요청 시 발생하는 예외 처리
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> methodNotAllowedExceptionHandler(
            HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(buildResponse(HttpStatus.METHOD_NOT_ALLOWED, exception.getMessage()));
    }

    // 클라이언트가 요청한 미디어 타입을 서버가 지원하지 않을 때 발생하는 예외 처리
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ExceptionResponse> notAcceptableExceptionHandler(HttpMediaTypeNotAcceptableException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(buildResponse(HttpStatus.NOT_ACCEPTABLE, exception.getMessage()));
    }

    // 클라이언트가 서버에서 지원하지 않는 미디어 타입으로 요청할 때 발생하는 예외 처리
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> unSupportedMediaTypeExceptionHandler(HttpMediaTypeNotSupportedException exception) {
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(buildResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, exception.getMessage()));
    }

    // 서버가 요청을 처리할 수 없는 상황에서 발생하는 예외들을 처리
    @ExceptionHandler({
            MissingPathVariableException.class,
            ConversionNotSupportedException.class,
            HttpMessageNotWritableException.class
    })
    public ResponseEntity<ExceptionResponse> internalServerErrorExceptionHandler(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
    }

    // 위에서 처리되지 않은 모든 일반적인 예외를 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> globalExceptionHandler(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
    }

    // 유효성 검사 실패 시 발생하는 예외를 처리하며, 설정된 메시지를 반환
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> validationFailedExceptionHandler(MethodArgumentNotValidException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(HttpStatus.BAD_REQUEST, exception.getFieldErrors().get(0).getDefaultMessage()));
    }

    // 파일 업로드 시, 파일 크기가 설정된 최대 크기를 초과할 때 발생하는 예외를 처리
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleMaxSizeException(MaxUploadSizeExceededException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    // 기본 예외 응답 빌더 메서드
    private ExceptionResponse buildResponse(HttpStatus status, String message) {
        return ExceptionResponse.builder()
                .status(status.value())
                .message(message)
                .build();
    }
}

