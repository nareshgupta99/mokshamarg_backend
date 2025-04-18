package com.example.MokshaMarg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.dto.UserDto;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.UserResponse;
import com.example.MokshaMarg.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public ResponseEntity<AbstractApiResponse<UserResponse>> getUserProfile() {
		AbstractApiResponse<UserResponse> abstractApiResponse =userService.getUserProfile();
		return new  ResponseEntity<AbstractApiResponse<UserResponse>>(abstractApiResponse,HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<AbstractApiResponse<UserResponse>> editUserProfile(@RequestBody UserDto user) {
		AbstractApiResponse<UserResponse> abstractApiResponse =userService.editUserProfile(user);
		return new  ResponseEntity<AbstractApiResponse<UserResponse>>(abstractApiResponse,HttpStatus.OK);
	}
	
	@PostMapping(value = "/edit/image",consumes = "multipart/form-data")
	public ResponseEntity<AbstractApiResponse<UserResponse>> editUserImage(@RequestPart(value = "image",required = false)  MultipartFile imageFile) {
		AbstractApiResponse<UserResponse> abstractApiResponse =userService.editUserImage(imageFile);
		return new  ResponseEntity<AbstractApiResponse<UserResponse>>(abstractApiResponse,HttpStatus.OK);
	}
	
	@PostMapping("/edit/password")
	public ResponseEntity<AbstractApiResponse<UserResponse>> updateUserPassword(@RequestBody UserDto userDto) {
		AbstractApiResponse<UserResponse> abstractApiResponse =userService.updateUserPassword(userDto);
		return new  ResponseEntity<AbstractApiResponse<UserResponse>>(abstractApiResponse,HttpStatus.OK);
	}
	
	
}
