package com.campro.common.controller;

import com.campro.common.exception.ApiException;
import com.campro.common.exception.ErrorCode;
import com.campro.common.controller.response.ApiResponse;
import jakarta.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<String>> apiExceptionHandler(ApiException exception) {
        log.info("Api exception: code: {}, message: {} exception: ", exception.getCode(), exception.getMessage(), exception);
        return ResponseEntity.ok().body(
                ApiResponse.from(
                        exception.getCode(),
                        exception.getMessage()
                )
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> nonExpectedException(RuntimeException exception) {
        log.warn("Non expected exception: message: {} exception: ", exception.getMessage(), exception);
        return ResponseEntity.ok().body(
                ApiResponse.from(
                        ErrorCode.NON_EXPECTED_EXCEPTION.getCode(),
                        ErrorCode.NON_EXPECTED_EXCEPTION.getMessage()
                )
        );
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        log.info("Missing request header: message: {} exception: ", exception.getMessage(), exception);
        return ResponseEntity.ok().body(
                ApiResponse.from(
                        ErrorCode.MISSING_REQUEST_HEADER.getCode(),
                        ErrorCode.MISSING_REQUEST_HEADER.getMessage()
                )
        );
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<ApiResponse<String>> servletExceptionHandler(ServletException exception) {
        log.warn("Non expected servlet exception: message: {} exception: ", exception.getMessage(), exception);
        return ResponseEntity.ok().body(
                ApiResponse.from(
                        ErrorCode.NON_EXPECTED_SERVLET_EXCEPTION.getCode(),
                        ErrorCode.NON_EXPECTED_SERVLET_EXCEPTION.getMessage()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.info("Method argument not valid: message: {} exception: ", exception.getMessage(), exception);
        List<FieldError> fieldErrors = exception.getFieldErrors();
        Map<String, String> response = new HashMap<>();

        fieldErrors.forEach(fieldError ->
                response.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        return ResponseEntity.ok().body(
                ApiResponse.from(
                        ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                        ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                        response
                )
        );
    }
}
