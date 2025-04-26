package com.example.MokshaMarg.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.dto.UserDto;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.exception.ResourceNotFoundExcepton;
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
		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundExcepton("user Not Found", email));
		System.out.println(email);
		UserResponse userResponse = new UserResponse();
		userResponse.setImage(user.getImage());
		userResponse.setName(user.getName());
		userResponse.setUserId(user.getUserId());
		userResponse.setRole(user.getRole().name());
		AbstractApiResponse<UserResponse> response = new AbstractApiResponse<UserResponse>(true, "success",
				userResponse);
		return response;
	}

	@Override
	public AbstractApiResponse<UserResponse> editUserProfile(UserDto userDto) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user Not Found"));
		user.setName(userDto.getName());
		userRepository.save(user);
		UserResponse userResponse = new UserResponse();
		userResponse.setImage(user.getImage());
		userResponse.setName(userDto.getName());
		userResponse.setUserId(user.getUserId());
		AbstractApiResponse<UserResponse> response = new AbstractApiResponse<UserResponse>(true, "success",
				userResponse);
		return response;
	}

	@Override
	public AbstractApiResponse<UserResponse> editUserImage(MultipartFile imageFile) {

		User existingUser = null;

		try {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundExcepton("user Not Found", email));
		if(user.getPublicId() !=null &&  user.getPublicId().length() > 0) {
			cloudinaryUploader.deleteFile(user.getPublicId());
		}
			Map<String, String> resp = cloudinaryUploader.uploadFile(imageFile);
			user.setImage(resp.get("url"));
			user.setPublicId(resp.get("public_id"));
			existingUser = userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserResponse userResponse = new UserResponse();
		userResponse.setImage(existingUser.getImage());
		userResponse.setName(existingUser.getName());
		userResponse.setUserId(existingUser.getUserId());
		AbstractApiResponse<UserResponse> response = new AbstractApiResponse<UserResponse>(true, "successl",
				userResponse);
		return response;
	}

	@Override
	public AbstractApiResponse<UserResponse> updateUserPassword(UserDto userDto) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user Not Found"));
		AbstractApiResponse<UserResponse> response = new AbstractApiResponse<UserResponse>();
		UserResponse userResponse = new UserResponse();
		if (!passwordEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
			response.setMessage("old password does not match");
		} else {
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			userRepository.save(user);
			response.setMessage("password Changed success");
		}
		response.setStatus(true);
		userResponse.setImage(user.getImage());
		userResponse.setName(user.getName());
		userResponse.setUserId(user.getUserId());
		response.setData(userResponse);
		return response;
	}
	
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public AbstractApiResponse<List<UserResponse>> getAllUser() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> users = userRepository.findAll();
	List<UserResponse> userResponses	= users.stream().map((user)->{
			UserResponse userResponse = new UserResponse();
			userResponse.setImage(user.getImage());
			userResponse.setName(user.getName());
			userResponse.setUserId(user.getUserId());
			userResponse.setRole(user.getRole().name());
			return userResponse;
			}).toList();
		
		AbstractApiResponse<List<UserResponse>> response = new AbstractApiResponse<List<UserResponse>>(true, "success",
				userResponses);
		return response;
	}


}
