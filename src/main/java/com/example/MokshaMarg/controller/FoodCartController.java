package com.example.MokshaMarg.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MokshaMarg.dto.CartItemDto;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.FoodCartResponse;
import com.example.MokshaMarg.service.FoodCartService;

@RestController
@RequestMapping("/api/v1/food/cart")
public class FoodCartController {
	
	@Autowired
	private FoodCartService foodCartService;
	
	@PostMapping("/add")
	public ResponseEntity<AbstractApiResponse<FoodCartResponse>>  addItemToCart(@RequestBody CartItemDto cartItem){
		AbstractApiResponse<FoodCartResponse> response = foodCartService.addItemToCart(cartItem);
		return new ResponseEntity<AbstractApiResponse<FoodCartResponse>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<AbstractApiResponse<List<FoodCartResponse>>>  getAllItemFromCart(){
		AbstractApiResponse<List<FoodCartResponse>> response = foodCartService.getAllItemFromCart();
		return new  ResponseEntity<AbstractApiResponse<List<FoodCartResponse>>>(response,HttpStatus.OK);
	}	
	
	@DeleteMapping("/all")
	public ResponseEntity<AbstractApiResponse<List<FoodCartResponse>>>  removeAllFromCart(){
		AbstractApiResponse<List<FoodCartResponse>> response = foodCartService.removeAllFromCart();
		return new ResponseEntity<AbstractApiResponse<List<FoodCartResponse>>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AbstractApiResponse<FoodCartResponse>>  removeFromCartById(@PathVariable Long id){
		AbstractApiResponse<FoodCartResponse> response = foodCartService.removeFromcart(id);
		return new ResponseEntity<AbstractApiResponse<FoodCartResponse>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/remove-one/{id}")
	public ResponseEntity<AbstractApiResponse<FoodCartResponse>>  removeQuantityFromCart(@PathVariable Long id){
		AbstractApiResponse<FoodCartResponse> response = foodCartService.removeQuantityFromCart(id);
		return new ResponseEntity<AbstractApiResponse<FoodCartResponse>>(response,HttpStatus.OK);
	}

}
