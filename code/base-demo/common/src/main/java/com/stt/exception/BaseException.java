package com.stt.exception;

import com.stt.constant.ExceptionCode;
/**
 * this not use lombok to avoid when project doing dynamic
 * complie java file throw lombok exception
 * Created by Administrator on 2019/1/31.
 */
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

	public int getCode(){return code;}

	@Override
	public String toString() {
		return "BaseException{" +
				"code=" + code +
				'}';
	}
}
