package com.armezo.util.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionController {
	
	// User Not Found Handler
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage userNotFoundException(UserNotFoundException exception, WebRequest request) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(new Date());
		error.setMessage(exception.getMessage());
		error.setDescription(request.getDescription(false));
		return error;
	}
	
	// Unauthorized
	@ExceptionHandler(UnauthorizedRequestException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ErrorMessage unauthorizedRequest(UnauthorizedRequestException exception, WebRequest request) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.UNAUTHORIZED.value());
		error.setTimeStamp(new Date());
		error.setMessage(exception.getMessage());
		error.setDescription(request.getDescription(false));
		return error;
	}
	// Global Exception 
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception exception, WebRequest request) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimeStamp(new Date());
		error.setMessage(exception.getMessage());
		error.setDescription(request.getDescription(false));
		return error;
	}

}
