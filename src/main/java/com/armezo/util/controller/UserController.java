package com.armezo.util.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.armezo.util.entity.UtilUser;
import com.armezo.util.exception.UnauthorizedRequestException;
import com.armezo.util.payload.MessagePayload;
import com.armezo.util.payload.UserRequestPayload;
import com.armezo.util.payload.UserResponsePayload;
import com.armezo.util.service.JwtUtilUserService;
import com.armezo.util.service.UtilUserService;
import com.armezo.util.utility.JwtTokenUtility;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UtilUserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtilUserService userDetailsService;
	@Autowired
	private JwtTokenUtility tokenUtil;
	
	@PostMapping("/saveUser")
	public ResponseEntity<?> saveUser(@RequestBody UserRequestPayload payload){
		ResponseEntity<String> responseEntity = null;
		//Load User by username
		Optional<UtilUser> userDetails = userService.getUserByUsername(payload.getUsername());
		if(userDetails.isEmpty()) {
			UtilUser user = new UtilUser();
			user.setUsername(payload.getUsername());
			user.setPassword(payload.getPassword());
			userService.saveUtilUser(user);
			responseEntity = new ResponseEntity<String>("User Details Saved",HttpStatus.CREATED);
		}
		else {
			responseEntity = new ResponseEntity<String>("User Already Exist", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	// Login Process

		@PostMapping("/login")
		public ResponseEntity<?> loginAndGetAccessToken(@RequestBody UserRequestPayload payload) throws Exception {
			ResponseEntity<?> responseEntity = null;
			MessagePayload msg = new MessagePayload();
			// Authenticate User
			String auth = authenticateUser(payload.getUsername(), payload.getPassword());
			// Load User
			if (auth.equalsIgnoreCase("Yes")) {
				final UserDetails userDetails = userDetailsService.loadUserByUsername(payload.getUsername());
				// Generate Token
				final String token = tokenUtil.generateToken(userDetails);
				System.out.println("Token Generated : "+token);
				responseEntity = ResponseEntity.ok(new UserResponsePayload(token));
			}else if (auth.equalsIgnoreCase("Bad") || auth.equalsIgnoreCase("No")) {
				msg.setMessage("Username/Password Not Matched");
				responseEntity= new ResponseEntity<MessagePayload>(msg, HttpStatus.BAD_REQUEST);
			}else if (auth.equalsIgnoreCase("Disable")) {
				msg.setMessage("User is disabled");
				responseEntity= new ResponseEntity<MessagePayload>(msg, HttpStatus.BAD_REQUEST);
			}
			return responseEntity;
		}
		
		
		private String authenticateUser(String username, String password) throws Exception {
			Objects.requireNonNull(username);
			Objects.requireNonNull(password);
			String authenticated = "";
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
				authenticated = "Yes";
			} catch (DisabledException e) {
				authenticated = "Diable";
				e.printStackTrace();
			} catch (BadCredentialsException e) {
				throw new UnauthorizedRequestException("Bad Username/Password");
//				authenticated = "Bad";
//				logger.error("Bad Credential");
			} catch (Unauthorized e) {
					throw new UnauthorizedRequestException("Username/Password is incorrect");
				//			authenticated = "No";
//				e.printStackTrace();
			} catch (Exception e) {
				authenticated = "No";
				e.printStackTrace();
			}
			return authenticated;

		}

}
