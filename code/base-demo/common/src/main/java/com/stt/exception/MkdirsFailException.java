package com.stt.exception;

import com.stt.constant.ExceptionCode;

/**
 * Created by Administrator on 2019/2/1.
 */
public class MkdirsFailException extends BaseException {
	public MkdirsFailException(String message) {
		super(message, ExceptionCode.FILE_MKDIRS_ERROR);
	}
}
