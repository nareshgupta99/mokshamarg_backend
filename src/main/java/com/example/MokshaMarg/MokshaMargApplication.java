package com.example.MokshaMarg;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.MokshaMarg.service.FoodTypeService;

@SpringBootApplication
public class MokshaMargApplication {
	
	@Autowired
	private FoodTypeService foodTypeService;

	public static void main(String[] args) {
		 ConfigurableApplicationContext context = SpringApplication.run(MokshaMargApplication.class, args);
	}
	
	
	
	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
