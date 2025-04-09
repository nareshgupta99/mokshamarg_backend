package com.example.MokshaMarg.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;


public interface RestaurantService {
    AbstractApiResponse registerRestaurant(String restaurant, MultipartFile imageFile);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(Long id);
    AbstractApiResponse updateRestaurant(Long id, Restaurant updatedRestaurant);
    AbstractApiResponse deleteRestaurant(Long id);
    public ResponseEntity<AbstractApiResponse> updateRestaurentUpdateStatus();
}

