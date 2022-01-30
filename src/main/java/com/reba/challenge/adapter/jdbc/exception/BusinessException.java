package com.reba.challenge.adapter.jdbc.exception;


import com.reba.challenge.config.ErrorCode;
import com.reba.challenge.config.exception.GenericException;

public class BusinessException extends GenericException {

	public BusinessException(ErrorCode errorCode){
		super(errorCode);
	}
}
