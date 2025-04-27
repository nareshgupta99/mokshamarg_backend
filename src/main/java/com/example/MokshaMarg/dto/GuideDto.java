package com.example.MokshaMarg.dto;

import java.util.List;

import com.example.MokshaMarg.entity.Language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideDto {
	
	private String guideId;
	
	private double price;
    private String imageUrl;
    private String createdAt;
     
    private List<String> languages;
}
