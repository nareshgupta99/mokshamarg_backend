package com.example.MokshaMarg.response;

import java.util.Set;

import com.example.MokshaMarg.entity.Language;
import com.example.MokshaMarg.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideResponse {
	
    private Long guideId;
	private double price;
    private String imageUrl;
    private String createdAt;
    private User user;
    private Set<Language> languages;

}
