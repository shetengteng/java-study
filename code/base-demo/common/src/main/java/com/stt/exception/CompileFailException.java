package com.stt.exception;

import com.stt.constant.ExceptionCode;

/**
 * Created by Administrator on 2019/1/31.
 */
public class CompileFailException extends BaseException{

	public CompileFailException(String message) {
		super(message, ExceptionCode.COMPILE_FAIL);
	}
}
