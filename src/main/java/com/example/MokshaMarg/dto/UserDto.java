package com.example.MokshaMarg.dto;

import com.example.MokshaMarg.util.OtpType;

import lombok.Data;


@Data
public class UserDto {

	private String userId;

	private String name;

	private String email;

	private String password;

	private String otp;

	private OtpType otpType;

	private boolean active;

	private String role;
	
	private String oldPassword;

}
