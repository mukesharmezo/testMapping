package com.armezo.util.payload;

public class TestMappingRequest {
	
	private String clientId;
	private String recruiterId;
	private String jobProfileId;
	private String url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "TestMappingRequest [clientId=" + clientId + ", recruiterId=" + recruiterId + ", jobProfileId="
				+ jobProfileId + ", url=" + url + "]";
	}
	
	

}
