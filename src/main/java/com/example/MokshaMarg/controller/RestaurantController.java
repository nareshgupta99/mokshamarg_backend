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
import com.example.MokshaMarg.response.RestaurentResponse;
import com.example.MokshaMarg.service.RestaurantService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

		@PostMapping(value = "/register", consumes = "multipart/form-data")
		public ResponseEntity<AbstractApiResponse<RestaurentResponse>> registerRestaurant(
				@RequestPart("restaurant") String restaurant,
				@RequestPart(value = "image", required = false) MultipartFile imageFile) {
	
			AbstractApiResponse<RestaurentResponse> response = restaurantService.registerRestaurant(restaurant, imageFile);
	
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}

	@GetMapping("")
	public ResponseEntity<AbstractApiResponse<List<RestaurentResponse>>> getAllRestaurants() {
		return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AbstractApiResponse<RestaurentResponse>> getRestaurantById(@PathVariable String id) {
		AbstractApiResponse<RestaurentResponse> restaurant = restaurantService.getRestaurantById(id);
		return restaurant != null ? new ResponseEntity<>(restaurant, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AbstractApiResponse<RestaurentResponse>> updateRestaurant(@PathVariable String id,
			@RequestBody Restaurant updatedRestaurant) {
		AbstractApiResponse<RestaurentResponse> response = restaurantService.updateRestaurant(id, updatedRestaurant);
		return response.isStatus() ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AbstractApiResponse<RestaurentResponse>> deleteRestaurant(@PathVariable String id) {
		AbstractApiResponse<RestaurentResponse> response = restaurantService.deleteRestaurant(id);
		return response.isStatus() ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/opening-status/{id}")
	public ResponseEntity<AbstractApiResponse<RestaurentResponse>> updateRestaurentUpdateStatus(@PathVariable String id,
			@RequestBody Restaurant restaurant) {
		AbstractApiResponse<RestaurentResponse> response = restaurantService.updateRestaurentUpdateStatus( restaurant.isOpen(),id);
		return response.isStatus() ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

}
