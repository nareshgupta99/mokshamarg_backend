package com.example.MokshaMarg.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.dto.UserDto;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.UserResponse;

public interface UserService {
	
	public AbstractApiResponse<UserResponse> getUserProfile();
	
	public AbstractApiResponse<UserResponse> editUserProfile(UserDto userDto);
	
	public AbstractApiResponse<UserResponse> editUserImage(MultipartFile imageFile);
	
	public AbstractApiResponse<UserResponse> updateUserPassword(UserDto userDto);

	AbstractApiResponse<List<UserResponse>> getAllUser();
	
	
}
