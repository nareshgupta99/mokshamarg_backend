package com.example.MokshaMarg.serviceImpl;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Dish;
import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.service.DishService;

public class DishServiceImpl implements DishService {

	@Override
	public AbstractApiResponse<Dish> addDish(String dish, MultipartFile imageFile) {
		
		return null;
	}

	@Override
	public AbstractApiResponse<List<Dish>> getAllDishes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractApiResponse<Dish> getDishById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractApiResponse<Dish> updateDish(Long id, Restaurant updatedRestaurant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractApiResponse<Void> deleteDish(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractApiResponse<Void> updateRestaurantOpeningStatus(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
