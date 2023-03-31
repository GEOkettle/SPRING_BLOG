package com.geo.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice //모든 Exception은 이곳으로 모여
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public Object handleArgException(Exception e) {



		CustomError error = new CustomError();
		error.setErrorMessage(e.getMessage());
		return error;
	}
}
