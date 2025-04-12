package com.example.MokshaMarg.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.FoodType;
import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.exception.ResourceNotFoundExcepton;
import com.example.MokshaMarg.repository.FoodTypeRepository;
import com.example.MokshaMarg.repository.RestaurentRepository;
import com.example.MokshaMarg.repository.UserRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.RestaurentResponse;
import com.example.MokshaMarg.service.RestaurantService;
import com.example.MokshaMarg.util.CloudinaryUploader;
import com.example.MokshaMarg.util.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.experimental.PackagePrivate;

@Service
public class RestaurentServiceImpl implements RestaurantService {

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
	private UserRepository userRepository;

	@Override
	@Transactional
	public AbstractApiResponse<RestaurentResponse> registerRestaurant(String restaurantjson, MultipartFile imageFile) {

	    AbstractApiResponse<RestaurentResponse> abstractResponse = null;

	    try {
	        // Convert JSON string to Restaurant object
	        ObjectMapper objectMapper = new ObjectMapper();
	        Restaurant restaurant = objectMapper.readValue(restaurantjson, Restaurant.class);

	        // Force this to be a new Restaurant (important to avoid Hibernate stale object exception)
	        restaurant.setRestaurantId(null);

	        // Upload image
	        @SuppressWarnings("unchecked")
	        Map<String, String> resp = cloudinaryUploader.uploadFile(imageFile);
	        restaurant.setImage(resp.get("url"));
	        restaurant.setPublicId("public_id");

	        // Resolve and attach FoodTypes (ensure managed entities are used)
	        List<FoodType> savedFoodTypes = new ArrayList<>();
	        for (FoodType ft : restaurant.getFoodTypes()) {
	            Optional<FoodType> existing = foodTypeRepository.findByName(ft.getName());
	            savedFoodTypes.add(existing.orElseGet(() -> foodTypeRepository.save(ft)));
	        }
	        restaurant.setFoodTypes(savedFoodTypes);

	        // Get current user from SecurityContext and attach
	        String email = SecurityContextHolder.getContext().getAuthentication().getName();
	        User savedUser = userRepository.findByEmail(email)
	            .orElseThrow(() -> new ResourceNotFoundExcepton(email, "Requested resource not found"));
	        restaurant.setUser(savedUser);

	        // Save restaurant
	        Restaurant savedRestaurant = restaurentRepository.save(restaurant);

	        // Map to response DTO
	        RestaurentResponse restaurentResponse = modelMapper.map(savedRestaurant, RestaurentResponse.class);

	        // Build API response
	        abstractResponse = new AbstractApiResponse<>(true, "Restaurant is created", restaurentResponse);

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
	public AbstractApiResponse<List<RestaurentResponse>> getAllRestaurants() {

		System.out.println("in restaurant");
		List<Restaurant> restaurants = restaurentRepository.findAll();
		List<RestaurentResponse> restaurentResponses = restaurants.stream()
				.map((rest) -> modelMapper.map(rest, RestaurentResponse.class)).toList();

		AbstractApiResponse<List<RestaurentResponse>> abstractResponse = new AbstractApiResponse<>(true,
				"success", restaurentResponses);
		return abstractResponse;
	}

	@Override
	public AbstractApiResponse<RestaurentResponse> getRestaurantById(Long id) {

		Restaurant restaurant = restaurentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExcepton(id.toString(), "Requested resourse not found"));

		RestaurentResponse restaurentResponse = modelMapper.map(restaurant, RestaurentResponse.class);
		AbstractApiResponse<RestaurentResponse> abstractResponse = new AbstractApiResponse<>(true,
				"success", restaurentResponse);
		return abstractResponse;
	}

	@Override
	@Transactional
	public AbstractApiResponse<RestaurentResponse> updateRestaurant(Long id, Restaurant restaurant) {

		List<FoodType> savedFoodTypes = new ArrayList<FoodType>();
		for (FoodType ft : restaurant.getFoodTypes()) {

			Optional<FoodType> existing = foodTypeRepository.findByName(ft.getName());
			if (existing.isPresent()) {
				savedFoodTypes.add(existing.get());
			} else {

				savedFoodTypes.add(foodTypeRepository.save(ft));
			}

		}
		restaurant.setFoodTypes(savedFoodTypes);
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User savedUser = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundExcepton(email, "Requested resource not found"));
        restaurant.setUser(savedUser);
		Restaurant savedResturant = restaurentRepository.save(restaurant);
		RestaurentResponse restaurentResponse = modelMapper.map(savedResturant, RestaurentResponse.class);
		  
		AbstractApiResponse<RestaurentResponse> abstractResponse = new AbstractApiResponse<>(true,
				"Restaurent is updated", restaurentResponse);
		return abstractResponse;
	}

	@Override
	public AbstractApiResponse<RestaurentResponse> deleteRestaurant(Long id) {

		return null;
	}

	@Override
	public AbstractApiResponse<RestaurentResponse> updateRestaurentUpdateStatus(Boolean open, Long id) {
		Restaurant restaurant = restaurentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExcepton(id.toString(), "Requested Resource Not found"));
		restaurant.setOpen(open);
		restaurant = restaurentRepository.save(restaurant);
		RestaurentResponse restaurentResponse = modelMapper.map(restaurant, RestaurentResponse.class);
		AbstractApiResponse<RestaurentResponse> abstractResponse = new AbstractApiResponse<>(true,
				"status updated", restaurentResponse);
		return abstractResponse;
	}
//	EmailDetails emailDetails=new EmailDetails("nareshgupta0899@gmail.com","testing","test", "" );
//	mailService.sendSimpleMail(emailDetails);

}
