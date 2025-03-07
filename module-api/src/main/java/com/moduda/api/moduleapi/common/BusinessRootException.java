package com.moduda.api.moduleapi.common;

public class BusinessRootException extends RuntimeException {
    private int statusCode;

    public BusinessRootException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode(){
        return statusCode;
    }
}
