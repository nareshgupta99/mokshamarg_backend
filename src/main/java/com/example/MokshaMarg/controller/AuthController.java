package com.example.MokshaMarg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MokshaMarg.dto.UserDto;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.LoginApiResponse;
import com.example.MokshaMarg.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationService authService;

	
	@GetMapping("/")
	public String get() {
		return "forwarded";
	}
	@PostMapping("/login")
	public ResponseEntity<AbstractApiResponse> login(@RequestBody UserDto loginRequest) {
		LoginApiResponse apiResponse = authService.login(loginRequest);
		AbstractApiResponse<LoginApiResponse> response = new AbstractApiResponse<>(true,
				"login success",apiResponse );
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/register")
	public ResponseEntity<AbstractApiResponse> register(@RequestBody User user) {
		AbstractApiResponse apiResponse = authService.register(user);
		if (apiResponse.isStatus()) {
			return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/sendOtp")
	public ResponseEntity<AbstractApiResponse> sendOtp(@RequestBody UserDto user) {
		AbstractApiResponse apiResponse = authService.sendOtp(user);
		if (apiResponse.isStatus()) {
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/verifyOtp")
	public ResponseEntity<AbstractApiResponse> verifyOtp(@RequestBody UserDto user) {
		AbstractApiResponse apiResponse = authService.verifyOtp(user);
		if (apiResponse.isStatus()) {
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	

	
	@PostMapping("/resetPassword")
	public ResponseEntity<AbstractApiResponse> resetPassword(@RequestBody UserDto user) {
		AbstractApiResponse apiResponse = authService.resetPassword(user);
		if (apiResponse.isStatus()) {
			return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
}
