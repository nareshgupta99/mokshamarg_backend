package com.example.MokshaMarg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.MokshaMarg.service.RestaurantService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	// Create
	@PostMapping(value = "/register", consumes = "multipart/form-data")
	public ResponseEntity<AbstractApiResponse> registerRestaurant(@RequestPart("restaurant") String restaurant,
			@RequestPart(value = "image", required = false) MultipartFile imageFile) {

		restaurantService.registerRestaurant(restaurant, imageFile);
//		System.out.println(restaurant);
//		System.out.println(imageFile.getOriginalFilename());

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// Read All
	@GetMapping
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
	}

	// Read By ID
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.getRestaurantById(id);
		return restaurant != null ? new ResponseEntity<>(restaurant, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Update
	@PutMapping("/edit/{id}")
	public ResponseEntity<AbstractApiResponse> updateRestaurant(@PathVariable Long id,
			@RequestBody Restaurant updatedRestaurant) {
		AbstractApiResponse response = restaurantService.updateRestaurant(id, updatedRestaurant);
		return response.isStatus() ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AbstractApiResponse> deleteRestaurant(@PathVariable Long id) {
		AbstractApiResponse response = restaurantService.deleteRestaurant(id);
		return response.isStatus() ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/opening-status/{id}")
	public ResponseEntity<AbstractApiResponse> updateRestaurentUpdateStatus(@PathVariable Long id) {
		AbstractApiResponse response = restaurantService.deleteRestaurant(id);
		return response.isStatus() ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}
	

}
