package com.armezo.util.payload;

public class UserResponsePayload {
	
private String accessToken;
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	
	
	public UserResponsePayload(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	@Override
	public String toString() {
		return "UserResponsePayload [accessToken=" + accessToken + "]";
	}
}
