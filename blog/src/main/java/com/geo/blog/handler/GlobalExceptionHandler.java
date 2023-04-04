package com.geo.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.geo.blog.dto.ResponseDto;


@ControllerAdvice //모든 Exception은 이곳으로 모여
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleArgException(Exception e) {

		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
		/*
		 * CustomError error = new CustomError(); error.setErrorMessage(e.getMessage());
		 * return error;
		 */
	}
}
