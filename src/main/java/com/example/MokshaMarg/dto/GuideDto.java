package com.example.MokshaMarg.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideDto {
	
	private Long guideId;
	
	private double price;
    private String imageUrl;
    private String createdAt;
     
    private Set<Long> languages;
}
