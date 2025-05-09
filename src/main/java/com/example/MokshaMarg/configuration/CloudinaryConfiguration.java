package com.example.MokshaMarg.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;



@Configuration
public class CloudinaryConfiguration {
	
	@Value("${cloud_name}")
	private String cloudName;
	
	@Value("${api_key}")
	private String apiKey;
	
	@Value("${api_secret}")
	private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cloudName,
                "api_key",apiKey,
                "api_secret",apiSecret 
                ));
    }
}
