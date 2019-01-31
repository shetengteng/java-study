package com.stt.exception;

import com.stt.constant.ExceptionCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
/**
 * Created by Administrator on 2019/1/31.
 */
@Getter
@ToString
public class BaseException extends RuntimeException {

	private int code;

	public BaseException(String message){
		this(message, ExceptionCode.UNKNOWN_ERROR);
	}

	public BaseException(String message,int code){
		super(message);
		this.code = code;
	}

	public BaseException(Throwable throwable){
		super(throwable);
		if(throwable instanceof BaseException){
			BaseException e = (BaseException) throwable;
			this.code = e.getCode();
		}else{
			this.code = ExceptionCode.UNKNOWN_ERROR;
		}
	}

}
