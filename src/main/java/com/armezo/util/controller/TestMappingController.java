package com.armezo.util.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armezo.util.entity.TestMapping;
import com.armezo.util.payload.MessagePayload;
import com.armezo.util.payload.TestMappingRequest;
import com.armezo.util.payload.UrlRequest;
import com.armezo.util.payload.UrlResponse;
import com.armezo.util.service.TestMappingService;

@RestController
@RequestMapping("/test")
public class TestMappingController {
	
	@Autowired
	private TestMappingService mappingService;
	
	@GetMapping("/welcome")
	public ResponseEntity<?> showWelcome(){
		System.out.println("Calleddddd");
		return new ResponseEntity<String>("Hello Sir",HttpStatus.OK);
	}
	
	//Save Test Mapping Data
	@PostMapping("/saveTestMapping")
	public ResponseEntity<?> saveTestMappingData(@RequestBody TestMappingRequest request){
		TestMapping mapping = new TestMapping();
		MessagePayload messagePayload = new MessagePayload();
		ResponseEntity<?> responseEntity = null;
		//Check for duplicate Data
		Optional<TestMapping> optionalMapping = mappingService.getTestMappingDataByClientIdRecIdJobProfile(request.getClientId(), request.getRecruiterId(), request.getJobProfileId());
		if(optionalMapping.isEmpty()) {
			mapping.setClientId(request.getClientId());
			mapping.setRecruiterId(request.getRecruiterId());
			mapping.setJobProfileId(request.getJobProfileId());
			mapping.setUrl(request.getUrl());
			//Save in DB
			mappingService.saveTestMappingData(mapping);
			messagePayload.setMessage("Test Mapping Data Saved");
			responseEntity = new ResponseEntity<MessagePayload>(messagePayload,HttpStatus.CREATED);
		}else {
			messagePayload.setMessage("Test Mapping Data Already Exist");
			responseEntity = new ResponseEntity<MessagePayload>(messagePayload,HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	//Get Url by passing data
	@PostMapping("/getUrl")
	public ResponseEntity<?> getUrlByPassingAllData(@RequestBody UrlRequest request){
		MessagePayload messagePayload = new MessagePayload();
		UrlResponse response = new UrlResponse();
		ResponseEntity<?> responseEntity = null;
		//Check for duplicate Data
		Optional<TestMapping> optionalMapping = mappingService.getTestMappingDataByClientIdRecIdJobProfile(request.getClientId(), request.getRecruiterId(), request.getJobProfileId());
		if(optionalMapping.isPresent()) {
			response.setTestUrl(optionalMapping.get().getUrl());
			responseEntity = new ResponseEntity<UrlResponse>(response,HttpStatus.FOUND);
		}else {
			messagePayload.setMessage("Test Mapping Data Not Found");
			responseEntity = new ResponseEntity<MessagePayload>(messagePayload,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

}
