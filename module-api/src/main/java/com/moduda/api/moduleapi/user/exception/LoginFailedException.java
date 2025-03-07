package com.moduda.api.moduleapi.user.exception;


import com.moduda.api.moduleapi.common.BusinessRootException;
import org.springframework.http.HttpStatus;

public class LoginFailedException extends BusinessRootException {
    public LoginFailedException() {
        super("아이디 또는 비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST.value());
    }
}
