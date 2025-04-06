package com.example.MokshaMarg.service;

import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.LoginApiResponse;

public interface AuthenticationService {
	
	public AbstractApiResponse register(User user);
	
	public LoginApiResponse login(User user);

}
