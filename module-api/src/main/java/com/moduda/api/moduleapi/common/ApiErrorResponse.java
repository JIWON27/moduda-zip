package com.moduda.api.moduleapi.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class ApiErrorResponse {
    private final int statusCode;
    private final String errorMessage;
    private final Map<String, String> detail;

    public ApiErrorResponse(int statusCode, String errorMessage, Map<String, String> detail) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.detail = detail;
    }

    public ApiErrorResponse(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.detail = null;
    }

    public ApiErrorResponse(Map<String, String> data) {
        this.statusCode = HttpStatus.BAD_REQUEST.value();
        this.errorMessage = "잘못된 요청입니다.";
        this.detail = data;
    }
}
