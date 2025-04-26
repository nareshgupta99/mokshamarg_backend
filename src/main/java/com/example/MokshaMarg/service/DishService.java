package com.example.MokshaMarg.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Dish;
import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.DishResponse;

public interface DishService {

	    AbstractApiResponse<DishResponse> addDish(String dish, MultipartFile imageFile);

	    AbstractApiResponse<List<DishResponse>> getAllDishes();

	    AbstractApiResponse<DishResponse> getDishById(String id);

	    AbstractApiResponse<DishResponse> updateDish(String restaurentId, Dish updatedDish, String dishId);

	    AbstractApiResponse<DishResponse> deleteDish(String id);

	    AbstractApiResponse<DishResponse> updateDishStatus(String DishId,String status);
	    
	    AbstractApiResponse<DishResponse> updateImage(String dishId,MultipartFile imageFile);
	    
	    public AbstractApiResponse<List<DishResponse>> getAllDishByRestaurant( String id);
}



