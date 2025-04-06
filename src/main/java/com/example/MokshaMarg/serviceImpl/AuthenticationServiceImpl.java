package com.example.MokshaMarg.serviceImpl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MokshaMarg.configuration.JwtUtil;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.exception.InvalidCredentialsException;
import com.example.MokshaMarg.repository.UserRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.LoginApiResponse;
import com.example.MokshaMarg.service.AuthenticationService;
import com.example.MokshaMarg.util.StatusType;

@Service	
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public AbstractApiResponse register(User user) {
	    Optional<User> optionalUser = userRepo.findByEmail(user.getEmail());

	    if (optionalUser.isEmpty()) {
	    	String encryptedPass= passwordEncoder.encode(user.getPassword());
	    	user.setPassword(encryptedPass);
	        userRepo.save(user);
	        return new AbstractApiResponse(true, "User registered successfully", Collections.emptyMap());
	    }

	    else {
	    	 return new AbstractApiResponse(true, "User already registered ", "");
	    }
	}

	@Override
	public LoginApiResponse  login(User loginRequest) {
		 Optional<User> optionalUser = userRepo.findByEmail(loginRequest.getEmail());

	    if (optionalUser.isEmpty()) {
	    	throw new UsernameNotFoundException("emails is not registerd");
	    }
	    User user = optionalUser.get();
	    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

	    	throw new InvalidCredentialsException("password is does not match");
	    }
	    
	    System.out.println(user);

		String token = jwtUtil.generateToken(loginRequest.getEmail(), user.getRole());
		LoginApiResponse response	=new LoginApiResponse();
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setToken(token);
		response.setRoles(user.getRole().name());
		response.setUserId(user.getUserId());
		return response;
	
	}
	
	
	

}
