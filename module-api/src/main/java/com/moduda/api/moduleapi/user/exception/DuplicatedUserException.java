package com.moduda.api.moduleapi.user.exception;

import com.moduda.api.moduleapi.common.BusinessRootException;
import org.springframework.http.HttpStatus;

public class DuplicatedUserException extends BusinessRootException {
    public DuplicatedUserException() {
        super("중복된 유저입니다.", HttpStatus.CONFLICT.value());
    }
}
