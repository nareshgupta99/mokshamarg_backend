package com.example.MokshaMarg.service;

import com.example.MokshaMarg.dto.UserDto;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.LoginApiResponse;
import com.example.MokshaMarg.response.OtpResponse;

public interface AuthenticationService {

	public LoginApiResponse login(UserDto user);

	public AbstractApiResponse<OtpResponse> sendOtp(UserDto user);

	public AbstractApiResponse resetPassword(UserDto user);

	public AbstractApiResponse verifyOtp(UserDto user);

	AbstractApiResponse register(User user);
}
