package com.example.MokshaMarg.service;

import com.example.MokshaMarg.dto.FoodOrderDto;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.FoodOrderResponse;

public interface FoodOrderService {
	public AbstractApiResponse<FoodOrderResponse> createOrder(FoodOrderDto foodOrderDto);
}
