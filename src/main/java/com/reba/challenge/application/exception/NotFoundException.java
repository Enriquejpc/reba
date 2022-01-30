package com.reba.challenge.application.exception;


import com.reba.challenge.adapter.jdbc.exception.BusinessException;
import com.reba.challenge.config.ErrorCode;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}