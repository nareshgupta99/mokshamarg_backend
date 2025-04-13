package com.example.MokshaMarg.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginApiResponse {



	private Long userId;

	private String name;

	private String email;

	private String roles;
	
	private boolean isRestaurentAdded;
	
	private boolean isGuideAdded;
	
	private String token;
	
	private String restaurantId;

}
