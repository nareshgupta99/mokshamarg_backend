package com.example.MokshaMarg.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.RestaurentResponse;

public interface RestaurantService {
	AbstractApiResponse<RestaurentResponse> registerRestaurant(String restaurant, MultipartFile imageFile);

	AbstractApiResponse<List<RestaurentResponse>> getAllRestaurants();

	AbstractApiResponse<RestaurentResponse> getRestaurantById(String id);

	AbstractApiResponse<RestaurentResponse> updateRestaurant(String id, Restaurant updatedRestaurant);

	AbstractApiResponse deleteRestaurant(String id);;

	AbstractApiResponse<RestaurentResponse> updateRestaurentUpdateStatus(Boolean open, String id);
}
