package com.armezo.util.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_mapping")
public class TestMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String clientId;
	private String recruiterId;
	private String jobProfileId;
	private String url;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	@Override
	public String toString() {
		return "TestMapping [id=" + id + ", clientId=" + clientId + ", recruiterId=" + recruiterId + ", jobProfileId="
				+ jobProfileId + ", url=" + url + "]";
	}
	
	
	
	

}
