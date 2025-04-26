package com.example.MokshaMarg.service;

import java.util.List;

import com.example.MokshaMarg.dto.CartItemDto;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.FoodCartResponse;

public interface FoodCartService {

	AbstractApiResponse<FoodCartResponse> addItemToCart(CartItemDto cartItemDto);

	AbstractApiResponse<FoodCartResponse> getCartById(String cartId);

	AbstractApiResponse<FoodCartResponse> removeFromcart(String cartItemId);

	AbstractApiResponse<List<FoodCartResponse>> getAllItemFromCart();

	AbstractApiResponse<List<FoodCartResponse>> removeAllFromCart();

	AbstractApiResponse<FoodCartResponse> removeQuantityFromCart(String id);

}
