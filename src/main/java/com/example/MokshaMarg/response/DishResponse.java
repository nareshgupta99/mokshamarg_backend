package com.example.MokshaMarg.response;

import java.util.List;

import com.example.MokshaMarg.entity.FoodType;
import com.example.MokshaMarg.entity.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {
	
	private Long dishId;

	private String dishName;
	private double price;

	private Restaurant restaurant;
	
	private String image;
	
	private List<FoodType> foodTypes;

}
