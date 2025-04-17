package com.example.MokshaMarg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempleDto {
	
    private Long id;
	
	private String name;
	
	private String streetAddress;
	
	private String city;
	
	private String liveLink;
	

}
