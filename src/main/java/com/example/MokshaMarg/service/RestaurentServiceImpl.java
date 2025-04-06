package com.example.MokshaMarg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;

@Service
public class RestaurentServiceImpl implements RestaurantService {

	@Override
	public AbstractApiResponse registerRestaurant(Restaurant restaurant, MultipartFile imageFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant getRestaurantById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractApiResponse updateRestaurant(Long id, Restaurant updatedRestaurant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractApiResponse deleteRestaurant(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
