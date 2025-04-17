package com.example.MokshaMarg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MokshaMarg.dto.FoodOrderDto;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.FoodOrderResponse;
import com.example.MokshaMarg.service.FoodOrderService;

@RestController
@RequestMapping("/api/v1/food/order")
public class FoodOrderController {
	

    @Autowired
    private FoodOrderService foodOrderService;

    @PostMapping("/create")
    public ResponseEntity<AbstractApiResponse<FoodOrderResponse>> createPayment(@RequestBody FoodOrderDto foodOrderDto) {
    	AbstractApiResponse<FoodOrderResponse> abstractApiResponse	   = foodOrderService.createOrder(foodOrderDto);
    	return new ResponseEntity<AbstractApiResponse<FoodOrderResponse>>(abstractApiResponse,HttpStatus.OK);
    }
    
    @PostMapping("/payment/verify")
    public ResponseEntity<AbstractApiResponse<FoodOrderResponse>> paymentVerify(@RequestBody FoodOrderDto foodOrderDto) {
    	AbstractApiResponse<FoodOrderResponse> abstractApiResponse	   = foodOrderService.createOrder(foodOrderDto);
    	return new ResponseEntity<AbstractApiResponse<FoodOrderResponse>>(abstractApiResponse,HttpStatus.OK);
    }

   

}
