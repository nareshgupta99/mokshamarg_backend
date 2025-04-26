package com.example.MokshaMarg.response;

import com.example.MokshaMarg.entity.FoodType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {
	
	private String dishId;

	private String dishName;
	
	private String image;
	
	private FoodType foodTypes;
	
	private double price;
	
	private String description;
	
	private String shortDescription;

}
