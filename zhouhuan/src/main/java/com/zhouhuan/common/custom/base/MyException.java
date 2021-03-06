package com.zhouhuan.common.custom.base;

public class MyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int exceptionCode;
	
	private String exceptionInfo;
	
	private Exception exception;

	public MyException(int exceptionCode) {
		super();
		this.exceptionCode = exceptionCode;
	}

	public MyException(int exceptionCode, String exceptionInfo) {
		super();
		this.exceptionCode = exceptionCode;
		this.exceptionInfo = exceptionInfo;
	}

	public MyException(int exceptionCode, String exceptionInfo,
			Exception exception) {
		super();
		this.exceptionCode = exceptionCode;
		this.exceptionInfo = exceptionInfo;
		this.exception = exception;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
