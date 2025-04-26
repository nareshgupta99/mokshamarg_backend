package com.example.MokshaMarg.response;

import java.util.List;

import com.example.MokshaMarg.entity.Dish;
import com.example.MokshaMarg.entity.FoodType;
import com.example.MokshaMarg.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurentResponse {
	private String restaurantId;

	private String name;
	private String address;
	private boolean open;
	private String openingTime;
	private String closeTime;
	private String latitude;
	private String longitude;
	private String image;
	private String startingPrice;
	private List<Dish> dishes;
	private List<FoodType> foodTypes;
}
