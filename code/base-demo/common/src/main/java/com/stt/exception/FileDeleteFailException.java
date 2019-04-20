package com.stt.exception;

import com.stt.constant.ExceptionCode;

/**
 * Created by Administrator on 2019/2/1.
 */
public class FileDeleteFailException extends BaseException {
	public FileDeleteFailException(String message) {
		super(message, ExceptionCode.FILE_DELETE_ERROR);
	}
}
