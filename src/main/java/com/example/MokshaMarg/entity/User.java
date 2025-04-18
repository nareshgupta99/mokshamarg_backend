package com.example.MokshaMarg.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
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

	private String otp;

	@Enumerated(EnumType.STRING)
	private OtpType otpType;

	private boolean active;

	private boolean verified = true;

	@Enumerated(EnumType.STRING)
	private RoleName role;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference("user_guideProfile")
	private Guide guideProfile;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private Restaurant restaurantOwnerProfile;
	
	private LocalTime expiryTime;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private FoodCart cart;
	
	@OneToMany
	@JsonIgnore
	private List <FoodOrder> foodOrder = new ArrayList<>();
	
	private String image ="http://res.cloudinary.com/dnkci1bpn/image/upload/v1745007026/nite6ivff9vnaok2epyt.png";
	
	private String publicId;

}
