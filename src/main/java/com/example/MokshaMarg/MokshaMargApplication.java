package com.example.MokshaMarg;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.MokshaMarg.service.FoodTypeService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class MokshaMargApplication {
	
	@Autowired
	private FoodTypeService foodTypeService;

	public static void main(String[] args) {
		SpringApplication.run(MokshaMargApplication.class, args);
		
	}
	
	@PostConstruct
	public void createFoodType() {
		foodTypeService.saveAllFoodType();
	}
	
	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
