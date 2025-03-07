package com.moduda.api.moduleapi.common;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleArgumentValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                errors.put(fieldName, message);
            }
        });

        ApiErrorResponse response = new ApiErrorResponse(errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BusinessRootException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessRootException(BusinessRootException ex){
        int statusCode = ex.getStatusCode();
        ApiErrorResponse response = new ApiErrorResponse(statusCode, ex.getMessage());
        return ResponseEntity.status(statusCode).body(response);

    }
}
