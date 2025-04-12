package com.example.MokshaMarg.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.dto.DishDto;
import com.example.MokshaMarg.entity.Dish;
import com.example.MokshaMarg.entity.FoodType;
import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.repository.DishRepository;
import com.example.MokshaMarg.repository.FoodTypeRepository;
import com.example.MokshaMarg.repository.RestaurentRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.DishResponse;
import com.example.MokshaMarg.service.DishService;
import com.example.MokshaMarg.util.CloudinaryUploader;
import com.example.MokshaMarg.util.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class DishServiceImpl implements DishService {

	@Autowired
	private CloudinaryUploader cloudinaryUploader;

	@Autowired
	private MailService mailService;

	@Autowired
	private FoodTypeRepository foodTypeRepository;

	@Autowired
	private RestaurentRepository restaurentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DishRepository dishRepository;

	@Override
	@Transactional
	public AbstractApiResponse<DishResponse> addDish(String dishJson, MultipartFile imageFile, Long restaurantId) {
		AbstractApiResponse<DishResponse> abstractResponse = null;
		System.out.println("res=="+restaurantId);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			DishDto dishDto = objectMapper.readValue(dishJson, DishDto.class);

			Dish dish = new Dish();
			dish.setDishName(dishDto.getDishName());
			dish.setPrice(dishDto.getPrice());
			@SuppressWarnings("unchecked")
			Map<String, String> resp = cloudinaryUploader.uploadFile(imageFile);
			dish.setImage(resp.get("url"));
			dish.setPublicId("public_id");
			Optional<FoodType> foodType = foodTypeRepository.findById(dishDto.getFoodTypeId());

			if (foodType.isPresent()) {
				dish.setFoodTypes(foodType.get());
			}
			Optional<Restaurant> restaurant = restaurentRepository.findById(restaurantId);

			if (restaurant.isPresent()) {
				dish.setRestaurant(restaurant.get());
			}
			dish = dishRepository.save(dish);
			DishResponse dishResponse = modelMapper.map(dish, DishResponse.class);

			abstractResponse = new AbstractApiResponse<DishResponse>(true, "Dish is created", dishResponse);

		} catch (JsonProcessingException e) {
			throw new RuntimeException("Invalid restaurant JSON format: " + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("Image upload failed: " + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong during registration: " + e.getMessage());
		}

		return abstractResponse;

	}

	@Override
	public AbstractApiResponse<List<DishResponse>> getAllDishes() {

		List<Dish> dish = dishRepository.findAll();
		
		List<DishResponse> dishResponses = dish.stream().map((d) -> modelMapper.map(d, DishResponse.class)).toList();

		AbstractApiResponse<List<DishResponse>> abstractApiResponse = new AbstractApiResponse<List<DishResponse>>(true,
				"success", dishResponses);
		return abstractApiResponse;
	}
	
	public AbstractApiResponse<List<DishResponse>> getAllDishByRestaurant( Long id){
		
		List<Dish> dish = dishRepository.findAll();
	List<Dish> filterDish =	dish.stream().filter((d)-> d.getRestaurant().getRestaurantId() == id ).toList();
	List<DishResponse> dishResponses = filterDish.stream().map((d) -> modelMapper.map(d, DishResponse.class)).toList();
		AbstractApiResponse<List<DishResponse>> abstractApiResponse = new AbstractApiResponse<List<DishResponse>>(true,
				"success", dishResponses);
		return abstractApiResponse;
	}

	@Override
	public AbstractApiResponse<DishResponse> getDishById(Long id) {
		Dish dish = dishRepository.findById(id).orElseThrow();
		DishResponse dishResponse = modelMapper.map(dish, DishResponse.class);
		AbstractApiResponse<DishResponse> abstractResponse = new AbstractApiResponse<DishResponse>(true,
				"Restaurant is created", dishResponse);
		return abstractResponse;
	}

	@Override
	public AbstractApiResponse<DishResponse> updateDish(Long restaurentId, Dish updatedDish, Long dishId) {

		return null;
	}

	@Override
	public AbstractApiResponse<DishResponse> deleteDish(Long dishId) {
		Dish dish = dishRepository.findById(dishId).orElseThrow();
		dish.setRestaurant(null);
		dish.setFoodTypes(null);
		dishRepository.delete(dish);
		DishResponse dishResponse = new DishResponse();
		dishResponse.setDishId(dish.getDishId());
		dishResponse.setDishName(dish.getDishName());
		dishResponse.setPrice(dish.getPrice());
		dishResponse.setImage(dish.getImage());

		AbstractApiResponse<DishResponse> abstractApiResponse = new AbstractApiResponse<DishResponse>(true,
				"image updated success", dishResponse);
		return abstractApiResponse;
	}

	@Override
	public AbstractApiResponse<DishResponse> updateDishStatus(Long DishId, String status) {

		return null;
	}

	@Override
	@Transactional
	public AbstractApiResponse<DishResponse> updateImage(Long dishId, MultipartFile imageFile) {

		AbstractApiResponse<DishResponse> abstractApiResponse = null;
		try {
			Dish dish = dishRepository.findById(dishId).orElseThrow();
			cloudinaryUploader.deleteFile(dish.getPublicId());
			Map<String, String> resMap = cloudinaryUploader.uploadFile(imageFile);
			dish.setPublicId(resMap.get("public_id"));
			dish.setImage(resMap.get("url"));
			dish = dishRepository.save(dish);
			DishResponse dishResponse = modelMapper.map(dish, DishResponse.class);
			abstractApiResponse = new AbstractApiResponse<DishResponse>(true, "image updated success", dishResponse);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return abstractApiResponse;
	}

}
