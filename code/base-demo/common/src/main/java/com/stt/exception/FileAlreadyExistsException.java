package com.stt.exception;

import com.stt.constant.ExceptionCode;

/**
 * Created by Administrator on 2019/1/31.
 */
public class FileAlreadyExistsException extends BaseException{

	public FileAlreadyExistsException(String filePath) {
		super(filePath + "has already existis", ExceptionCode.FILE_EXISTS_ERROR);
	}
}
