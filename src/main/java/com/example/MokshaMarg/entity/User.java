package com.example.MokshaMarg.entity;

import java.time.LocalTime;

import com.example.MokshaMarg.util.OtpType;
import com.example.MokshaMarg.util.RoleName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String name;

	private String email;

	private String password;

	private int otp;

	@Enumerated(EnumType.STRING)
	private OtpType otpType;

	private boolean active;

	private boolean verified = true;

	@Enumerated(EnumType.STRING)
	private RoleName role;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Guide guideProfile;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Restaurant restaurantOwnerProfile;
	
	private LocalTime expiryTime;

}
