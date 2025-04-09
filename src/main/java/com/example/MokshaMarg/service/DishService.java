package com.example.MokshaMarg.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Dish;
import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;

public interface DishService {

	    AbstractApiResponse<Dish> addDish(String dish, MultipartFile imageFile);

	    AbstractApiResponse<List<Dish>> getAllDishes();

	    AbstractApiResponse<Dish> getDishById(Long id);

	    AbstractApiResponse<Dish> updateDish(Long id, Restaurant updatedRestaurant);

	    AbstractApiResponse<Void> deleteDish(Long id);

	    AbstractApiResponse<Void> updateRestaurantOpeningStatus(Long id);
	}



