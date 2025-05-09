package com.example.MokshaMarg.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.dto.DishDto;
import com.example.MokshaMarg.entity.Dish;
import com.example.MokshaMarg.entity.FoodType;
import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.exception.ResourceNotFoundExcepton;
import com.example.MokshaMarg.repository.DishRepository;
import com.example.MokshaMarg.repository.FoodTypeRepository;
import com.example.MokshaMarg.repository.RestaurentRepository;
import com.example.MokshaMarg.repository.UserRepository;
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
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public AbstractApiResponse<DishResponse> addDish(String dishJson, MultipartFile imageFile) {
		AbstractApiResponse<DishResponse> abstractResponse = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			DishDto dishDto = objectMapper.readValue(dishJson, DishDto.class);

			Dish dish = new Dish();
			dish.setDishName(dishDto.getDishName());
			dish.setPrice(dishDto.getPrice());
			@SuppressWarnings("unchecked")
			Map<String, String> resp = cloudinaryUploader.uploadFile(imageFile);
			dish.setImage(resp.get("url"));
			dish.setPublicId(resp.get("public_id"));
			dish.setDishId(UUID.randomUUID().toString());;
			
		
			System.out.println("food types::"+dishDto.getFoodTypes());
			FoodType foodType = foodTypeRepository.findByName(dishDto.getFoodTypes()).orElseThrow(()->new ResourceNotFoundExcepton("id","resource not found"));
				
			
				dish.setFoodTypes(foodType);
				String email =SecurityContextHolder .getContext().getAuthentication().getName();
				User existingUser = userRepository.findByEmail(email).orElseThrow();
			Restaurant restaurant = restaurentRepository.findByUser(existingUser).orElseThrow(()->new ResourceNotFoundExcepton("restauran","resource not found"));

			
				dish.setRestaurant(restaurant);
			
			dish = dishRepository.save(dish);
//			DishResponse dishResponse = modelMapper.map(dish, DishResponse.class);
//			System.out.println(dishResponse.getFoodTypes());

			abstractResponse = new AbstractApiResponse<DishResponse>(true, "Dish is created",new DishResponse());

		} catch (JsonProcessingException e) {
			throw new RuntimeException("Invalid restaurant JSON format: " + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("Image upload failed: " + e.getMessage());
		}catch (ResourceNotFoundExcepton e) {
			throw new RuntimeException("resource not found: " + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong during add dish: " + e.getMessage());
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
	
	public AbstractApiResponse<List<DishResponse>> getAllDishByRestaurant( String id){
		
		List<Dish> dish = dishRepository.findAll();
	List<Dish> filterDish =	dish.stream().filter((d)-> d.getRestaurant().getRestaurantId().equals( id) ).toList();
	List<DishResponse> dishResponses = filterDish.stream().map((d) -> modelMapper.map(d, DishResponse.class)).toList();
		AbstractApiResponse<List<DishResponse>> abstractApiResponse = new AbstractApiResponse<List<DishResponse>>(true,
				"success", dishResponses);
		return abstractApiResponse;
	}

	@Override
	public AbstractApiResponse<DishResponse> getDishById(String id) {
		Dish dish = dishRepository.findById(id).orElseThrow();
		DishResponse dishResponse = modelMapper.map(dish, DishResponse.class);
		AbstractApiResponse<DishResponse> abstractResponse = new AbstractApiResponse<DishResponse>(true,
				"Restaurant is created", dishResponse);
		return abstractResponse;
	}

	@Override
	public AbstractApiResponse<DishResponse> updateDish(String restaurentId, Dish updatedDish, String dishId) {

		return null;
	}

	@Override
	public AbstractApiResponse<DishResponse> deleteDish(String dishId) {
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
				"Dish Deleted", dishResponse);
		return abstractApiResponse;
	}

	@Override
	public AbstractApiResponse<DishResponse> updateDishStatus(String DishId, String status) {

		return null;
	}

	@Override
	@Transactional
	public AbstractApiResponse<DishResponse> updateImage(String dishId, MultipartFile imageFile) {

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
