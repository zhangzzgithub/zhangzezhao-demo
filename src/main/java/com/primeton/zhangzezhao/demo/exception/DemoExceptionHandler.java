package com.primeton.zhangzezhao.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.primeton.zhangzezhao.demo.entity.ResponseResult;

/**
 * 全局异常处理类
 * @author zhao1
 *
 */
@CrossOrigin
@RestControllerAdvice
public class DemoExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoExceptionHandler.class);
	
	@ExceptionHandler(DemoException.class)
	public ResponseResult<Void> handleException(Exception e){
		LOGGER.error(e.getMessage(),e);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		if(e instanceof DemoException) {
			DemoException demoException = (DemoException) e;
			rr.setState(ResponseResult.STATE_ERR);
			rr.setCode(demoException.getCode());
			rr.setMessage(demoException.getMessage());
			return rr;
		}else {
			rr.setState(ResponseResult.STATE_ERR);
			rr.setCode(ErrorEnum.SYSTEM_ERROR+"");
			rr.setMessage(e.getMessage());
			return rr;
		}
	}
}
