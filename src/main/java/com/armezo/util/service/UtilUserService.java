package com.armezo.util.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.armezo.util.entity.UtilUser;
import com.armezo.util.repository.UtilUserRepository;

@Service
public class UtilUserService {
	
	@Autowired
	private UtilUserRepository utilRepository;
	@Autowired
	private PasswordEncoder encoder;
	
	//Find User by username
	public Optional<UtilUser> getUserByUsername(String username){
		return utilRepository.findByUsername(username);
	}
	
	//Save System User
	public void saveUtilUser(UtilUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		utilRepository.save(user);
	}

}
