package com.stt.exception;

import com.stt.constant.ExceptionCode;
import org.springframework.http.HttpStatus;

/**
 * Created by Administrator on 2019/1/31.
 */
public class PersistenceException extends BaseException{

	public PersistenceException(String message){
		super(message, ExceptionCode.PERSISTENCE_ERROR);
	}

}
