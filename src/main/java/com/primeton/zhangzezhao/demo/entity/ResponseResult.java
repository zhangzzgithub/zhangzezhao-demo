package com.primeton.zhangzezhao.demo.entity;
/**
 * 响应实体类
 */
public class ResponseResult<T> {
	
	public static final Integer STATE_OK = 1;
	public static final Integer STATE_ERR = 0;
	
	private Integer state;
	private String code;
	private String message;
	private T data;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ResponseResult() {
		super();
	}
	
	public ResponseResult(Integer state) {
		super();
		setState(state);
	}
	
	public ResponseResult(Integer state, String message) {
		super();
		setState(state);
		setMessage(message);
	}

	public ResponseResult(String code, String message) {
		super();
		setCode(code);
		setMessage(message);
	}
	
	public ResponseResult(Exception e) {
		super();
		setState(STATE_ERR);
		setMessage(e.getMessage());
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}
