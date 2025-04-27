package com.example.MokshaMarg.response;

import java.util.List;

import com.example.MokshaMarg.entity.Language;
import com.example.MokshaMarg.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideResponse {
	
    private String guideId;
	private double price;
    private String imageUrl;
    private String createdAt;
    private List<Language> languages;

}
