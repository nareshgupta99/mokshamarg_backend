package com.example.MokshaMarg.serviceImpl;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MokshaMarg.configuration.JwtUtil;
import com.example.MokshaMarg.dto.UserDto;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.exception.InvalidCredentialsException;
import com.example.MokshaMarg.repository.RestaurentRepository;
import com.example.MokshaMarg.repository.UserRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.LoginApiResponse;
import com.example.MokshaMarg.response.OtpResponse;
import com.example.MokshaMarg.service.AuthenticationService;
import com.example.MokshaMarg.util.EmailDetails;
import com.example.MokshaMarg.util.MailBody;
import com.example.MokshaMarg.util.MailService;

@Service	
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private UserRepository userRepo;


	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MailService mailService;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RestaurentRepository restaurentRepository;
	

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
	public LoginApiResponse  login(UserDto loginRequest) {
		 Optional<User> optionalUser = userRepo.findByEmail(loginRequest.getEmail());

	    if (optionalUser.isEmpty()) {
	    	throw new UsernameNotFoundException("emails is not registerd");
	    }
	    User user = optionalUser.get();
	    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

	    	throw new InvalidCredentialsException("password is does not match");
	    }
	    
	    
		String token = jwtUtil.generateToken(loginRequest.getEmail(), user.getRole());
		LoginApiResponse response	=new LoginApiResponse();
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setToken(token);
		response.setRoles(user.getRole().name());
		response.setUserId(user.getUserId());
		response.setRestaurentAdded(user.getRestaurantOwnerProfile() !=null ? true :false);
		if(user.getRestaurantOwnerProfile() !=null) {
			response.setRestaurantId(user.getRestaurantOwnerProfile().getRestaurantId()+"");
		}
		//		response.setGuideAdded(user.getGuideProfile() !=null ? true :false);
		return response;
	
	}
	
	
	public AbstractApiResponse<OtpResponse> sendOtp(User user) {
		 Optional<User> optionalUser = userRepo.findByEmail(user.getEmail());
		    if (optionalUser.isEmpty()) {
		    	throw new UsernameNotFoundException("emails is not registerd");
		    }
		    User saveduser = optionalUser.get();
		    int otp = (int)(Math.random() * 1000000);
		    String otpString = String.format("%06d", otp);
		   System.out.println("otp == "+  otp );
		   LocalTime now=LocalTime.now();
		   LocalTime expiry =now.plusMinutes(2);
		   saveduser.setExpiryTime(expiry);
		   saveduser.setOtp(otpString);
		   userRepo.save(saveduser);
		   EmailDetails details= new EmailDetails();
		   
		   String messageBody=MailBody.resetPasswordBody(saveduser.getName(), otpString);
		   
		   details.setMsgBody(messageBody);
		   details.setRecipient(saveduser.getEmail());
		   details.setSubject("Password Reset Request");
		   mailService.sendSimpleMail(details);
		   OtpResponse response =new OtpResponse();
		   response.setEmail(user.getEmail());
		   response.setId(saveduser.getUserId());
		   return new AbstractApiResponse<OtpResponse>(true,"otp sent successfully",response);
	}
	
	public void verifyOtp(User user) {
		 Optional<User> optionalUser = userRepo.findByEmail(user.getEmail());
		    if (optionalUser.isEmpty()) {
		    	throw new UsernameNotFoundException("emails is not registerd");
		    }
		    User saveduser = optionalUser.get();
		   LocalTime expiry =saveduser.getExpiryTime();
		   LocalTime now=LocalTime.now();
		   if (now.isAfter(expiry)) {
			    System.out.println("expiry");
			} else {
			    System.out.println("Outside working hours.");
			}
		   userRepo.save(saveduser);
	}
	
	public void resetPassword(User user) {
		 Optional<User> optionalUser = userRepo.findByEmail(user.getEmail());
		    if (optionalUser.isEmpty()) {
		    	throw new UsernameNotFoundException("emails is not registerd");
		    }
		    User saveduser = optionalUser.get();
		    String encodedPassword=passwordEncoder.encode(user.getPassword());
		    saveduser.setPassword(encodedPassword);
		    userRepo.save(saveduser);
		
	}
	

	
	
	

}
