package com.example.MokshaMarg.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
	private String userId;
	
	private String name;
	
	private String image;
	
	private String role;

}
