package com.reba.challenge.application.exception;


import com.reba.challenge.config.ErrorCode;
import com.reba.challenge.config.exception.GenericException;

public class AdapterException extends GenericException {

    public AdapterException(ErrorCode ec){ super(ec);}
}
