package com.primeton.zhangzezhao.demo.exception;

/**
 * 全局异常类
 */
public class DemoException extends RuntimeException{

	private static final long serialVersionUID = -2621108581343428936L;

	private String code;
	private String message;
	
	public DemoException() {
		super();
	}

	public DemoException(ErrorEnum errorEnum) {
		this.code = errorEnum.getCode();
		this.message = errorEnum.getMsg();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
