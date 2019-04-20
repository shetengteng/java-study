package com.stt.exception;

import com.stt.constant.ExceptionCode;

/**
 * Created by Administrator on 2019/2/1.
 */
public class FileCreateFailException extends BaseException {

	public FileCreateFailException(String message) {
		super(message, ExceptionCode.FILE_CREATE_ERROR);
	}
}
