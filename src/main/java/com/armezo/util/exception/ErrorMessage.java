package com.armezo.util.exception;

import java.util.Date;

public class ErrorMessage {
	
	private int errorCode;
	private String message;
	private String description;
	private Date timeStamp;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "ErrorMessage [errorCode=" + errorCode + ", message=" + message + ", description=" + description
				+ ", timeStamp=" + timeStamp + "]";
	}
	
	
	

}
