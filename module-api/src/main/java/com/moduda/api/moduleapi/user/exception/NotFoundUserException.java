package com.moduda.api.moduleapi.user.exception;

import com.moduda.api.moduleapi.common.BusinessRootException;
import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BusinessRootException {
    public NotFoundUserException() {
        super("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND.value());
    }
}
