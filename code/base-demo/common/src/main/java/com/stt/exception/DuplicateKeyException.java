package com.stt.exception;

import com.stt.constant.ExceptionCode;

/**
 * Created by Administrator on 2019/1/31.
 */
public class DuplicateKeyException extends BaseException {

	public DuplicateKeyException(String message) {
		super(message, ExceptionCode.DUPLICATE_KEY);
	}
}
