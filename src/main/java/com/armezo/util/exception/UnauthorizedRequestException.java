package com.armezo.util.exception;

public class UnauthorizedRequestException extends RuntimeException {


	private static final long serialVersionUID = 3526053113380585783L;
	
	public UnauthorizedRequestException(String message) {
		super(message);
	}

}
