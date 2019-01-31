package com.stt.exception;

/**
 * Created by Administrator on 2019/1/31.
 */
public class ServerException extends BaseException {

	public ServerException(String message) {
		super(message);
	}

	public ServerException(String message,int code){
		super(message,code);
	}

}
