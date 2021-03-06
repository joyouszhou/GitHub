package com.zhouhuan.common.custom;

import com.zhouhuan.common.custom.base.MyException;

public class SystemException extends MyException{
	private static final long serialVersionUID = 1L;

	public SystemException(int exceptionCode) {
		super(exceptionCode);
	}

	public SystemException(int exceptionCode, String exceptionInfo) {
		super(exceptionCode, exceptionInfo);
	}
	
	public SystemException(int exceptionCode, String exceptionInfo, Exception exception) {
		super(exceptionCode, exceptionInfo, exception);
	}
}
