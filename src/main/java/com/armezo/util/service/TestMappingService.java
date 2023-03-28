package com.armezo.util.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.util.entity.TestMapping;
import com.armezo.util.repository.TestMappingRepository;

@Service
public class TestMappingService {
	
	@Autowired
	private TestMappingRepository mappingRepository;
	
	//Save Test Mapping Data
	public void saveTestMappingData(TestMapping mapping) {
		mappingRepository.save(mapping);
	}
	
	// Get Mapping Data by Client Id, Rec Id and Job Profile
	public Optional<TestMapping> getTestMappingDataByClientIdRecIdJobProfile(String clientId, String recruiterId, String jobProfile){
		Optional<TestMapping> mapping = mappingRepository.getTestMappingDataByClientIdRecIdJobProfile(clientId,recruiterId,jobProfile);
		return mapping;
	}
	

}
