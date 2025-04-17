package com.example.MokshaMarg.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.dto.UserDto;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.repository.UserRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.UserResponse;
import com.example.MokshaMarg.service.UserService;
import com.example.MokshaMarg.util.CloudinaryUploader;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CloudinaryUploader cloudinaryUploader;

	@Override
	public AbstractApiResponse<UserResponse> getUserProfile() {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user Not Found"));
		UserResponse userResponse = new UserResponse();
		userResponse.setImage("");
		userResponse.setName(user.getName());
		userResponse.setUserId(userResponse.getUserId());
		AbstractApiResponse<UserResponse> response = new AbstractApiResponse<UserResponse>(true, "successl", userResponse);
		return response;
	}

	@Override
	public AbstractApiResponse<UserResponse> editUserProfile(UserDto userDto) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user Not Found"));
		user.setName(userDto.getName());
		userRepository.save(user);
		UserResponse userResponse = new UserResponse();
		userResponse.setImage("");
		userResponse.setName(userDto.getName());
		userResponse.setUserId(userResponse.getUserId());
		AbstractApiResponse<UserResponse> response = new AbstractApiResponse<UserResponse>(true, "successl", userResponse);
		return response;
	}

	@Override
	public AbstractApiResponse<UserResponse> editUserImage(MultipartFile imageFile) {
		
		User existingUser=null;
		
		try {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user Not Found"));
		cloudinaryUploader.deleteFile(user.getPublicId());
		Map<String, String> resp = cloudinaryUploader.uploadFile(imageFile);
		user.setImage(resp.get("url"));
		user.setPublicId(resp.get("public_id"));
		existingUser	= userRepository.save(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		UserResponse userResponse = new UserResponse();
		userResponse.setImage(existingUser.getImage());
		userResponse.setName(existingUser.getName());
		userResponse.setUserId(existingUser.getUserId());
		AbstractApiResponse<UserResponse> response = new AbstractApiResponse<UserResponse>(true, "successl", userResponse);
		return response;
	}

	@Override
	public AbstractApiResponse<UserResponse> updateUserPassword(UserDto userDto) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user Not Found"));
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepository.save(user);
		UserResponse userResponse = new UserResponse();
		userResponse.setImage(user.getImage());
		userResponse.setName(userDto.getName());
		userResponse.setUserId(userResponse.getUserId());
		AbstractApiResponse<UserResponse> response = new AbstractApiResponse<UserResponse>(true, "succesful", userResponse);
		return response;
	}
	

}
