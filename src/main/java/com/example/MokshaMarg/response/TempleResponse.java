package com.example.MokshaMarg.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempleResponse {
	
	private Long id;
	
	private String name;
	
	private String streetAddress;
	
	private String city;
	
	private String Livelink;
	
	private String image;
	
}
