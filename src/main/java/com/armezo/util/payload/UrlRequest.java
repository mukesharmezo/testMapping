package com.armezo.util.payload;

public class UrlRequest {
	
	private String clientId;
	private String recruiterId;
	private String jobProfileId;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getJobProfileId() {
		return jobProfileId;
	}
	public void setJobProfileId(String jobProfileId) {
		this.jobProfileId = jobProfileId;
	}
	@Override
	public String toString() {
		return "UrlRequest [clientId=" + clientId + ", recruiterId=" + recruiterId + ", jobProfileId=" + jobProfileId
				+ "]";
	}
	
	

}
