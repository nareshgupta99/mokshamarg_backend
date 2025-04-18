package com.example.MokshaMarg.dto;

import java.time.LocalTime;

import com.example.MokshaMarg.util.OtpType;
import com.example.MokshaMarg.util.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
public class UserDto {

	private Long userId;

	private String name;

	private String email;

	private String password;

	private String otp;

	private OtpType otpType;

	private boolean active;

	private String role;
	
	private String oldPassword;

}
