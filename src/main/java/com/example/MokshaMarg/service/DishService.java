package com.example.MokshaMarg.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Dish;
import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.DishResponse;

public interface DishService {

	    AbstractApiResponse<DishResponse> addDish(String dish, MultipartFile imageFile, Long restaurantId);

	    AbstractApiResponse<List<DishResponse>> getAllDishes();

	    AbstractApiResponse<DishResponse> getDishById(Long id);

	    AbstractApiResponse<DishResponse> updateDish(Long restaurentId, Dish updatedDish, Long dishId);

	    AbstractApiResponse<DishResponse> deleteDish(Long id);

	    AbstractApiResponse<DishResponse> updateDishStatus(Long DishId,String status);
	    
	    AbstractApiResponse<DishResponse> updateImage(Long dishId,MultipartFile imageFile);
	    
	    public AbstractApiResponse<List<DishResponse>> getAllDishByRestaurant( Long id);
}



