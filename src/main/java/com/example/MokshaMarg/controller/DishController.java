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

import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.DishResponse;
import com.example.MokshaMarg.service.DishService;

@RestController
@RequestMapping("/api/v1/dish")
public class DishController {

	@Autowired
	private DishService dishService;

	@PostMapping(value = "/add/restaurant/{id}", consumes = "multipart/form-data")
	public ResponseEntity<AbstractApiResponse<DishResponse>> addDish(@RequestPart("dish") String dish,
			@RequestPart(value = "image", required = false) MultipartFile imageFile, @PathVariable Long id) {

		AbstractApiResponse<DishResponse> abstractApiResponse = dishService.addDish(dish, imageFile, id);
		return new ResponseEntity<>(abstractApiResponse, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<AbstractApiResponse<List<DishResponse>>> getAllDish() {

		AbstractApiResponse<List<DishResponse>> abstractApiResponse = dishService.getAllDishes();
		return new ResponseEntity<>(abstractApiResponse, HttpStatus.OK);
	}

	@GetMapping("/restaurant/{id}")
	public ResponseEntity<AbstractApiResponse<List<DishResponse>>> getAllDishByRestaurant(@PathVariable Long id) {
		AbstractApiResponse<List<DishResponse>> abstractApiResponse = dishService.getAllDishByRestaurant(id);
		return new ResponseEntity<>(abstractApiResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AbstractApiResponse<DishResponse>> getDishById(@PathVariable Long id) {

		AbstractApiResponse<DishResponse> abstractApiResponse = dishService.getDishById(id);
		return new ResponseEntity<>(abstractApiResponse, HttpStatus.OK);

	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<AbstractApiResponse<DishResponse>> updateDish(@PathVariable Long id,
			@RequestBody Restaurant updatedRestaurant) {

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AbstractApiResponse<DishResponse>> deleteRestaurant(@PathVariable Long id) {

		AbstractApiResponse<DishResponse> abstractApiResponse = dishService.deleteDish(id);
		return new ResponseEntity<>(abstractApiResponse, HttpStatus.OK);
	}

	@PutMapping("/update/opening-status/{id}")
	public ResponseEntity<AbstractApiResponse<DishResponse>> updateDishStatus(@PathVariable Long id,
			@RequestBody String status) {

		AbstractApiResponse<DishResponse> abstractApiResponse = dishService.updateDishStatus(id, status);
		return new ResponseEntity<>(abstractApiResponse, HttpStatus.OK);

	}

	@PutMapping("/update/dish/image/{id}")
	public ResponseEntity<AbstractApiResponse<DishResponse>> updateImage(@PathVariable Long id,
			@RequestPart(value = "image", required = true) MultipartFile imageFile) {

		AbstractApiResponse<DishResponse> abstractApiResponse = dishService.updateImage(id, imageFile);
		return new ResponseEntity<>(abstractApiResponse, HttpStatus.OK);
	}

}
