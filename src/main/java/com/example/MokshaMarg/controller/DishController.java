package com.example.MokshaMarg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Dish;
import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.service.DishService;

@RestController
@RequestMapping("/api/v1/dish")
public class DishController {
	
//	@Autowired
//	private DishService dishService;
	
	@PostMapping(value="/",consumes = "multipart/form-data")
	public ResponseEntity<AbstractApiResponse> addDish(@RequestPart("dish") String dish,
			@RequestPart(value = "image", required = false) MultipartFile imageFile) {
		
//		dishService.addDish(dish, imageFile);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<AbstractApiResponse<List<Dish>>> getAllDish() {
		return new ResponseEntity<>( HttpStatus.OK);
	}

	// Read By ID
	@GetMapping("/{id}")
	public ResponseEntity<AbstractApiResponse<Dish>> getDishById(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Update
	@PutMapping("/edit/{id}")
	public ResponseEntity<AbstractApiResponse> updateDish(@PathVariable Long id,
			@RequestBody Restaurant updatedRestaurant) {
		
		return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
	}

	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AbstractApiResponse> deleteRestaurant(@PathVariable Long id) {
		
		return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/opening-status/{id}")
	public ResponseEntity<AbstractApiResponse> updateRestaurentUpdateStatus(@PathVariable Long id) {
		
		return new ResponseEntity<>( HttpStatus.NOT_FOUND);

	}
	

}
