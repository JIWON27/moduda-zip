package com.moduda.api.moduleapi.user.exception;

import com.moduda.api.moduleapi.common.BusinessRootException;
import org.springframework.http.HttpStatus;

public class UpdatePasswordFailException extends BusinessRootException {
    public UpdatePasswordFailException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
