package com.armezo.util.payload;

public class MessagePayload {
	
	private String message;
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	@Override
	public String toString() {
		return "MessagePayload [message=" + message + "]";
	}
	
	

}
