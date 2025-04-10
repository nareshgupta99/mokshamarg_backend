package com.example.MokshaMarg.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.service.RestaurantService;
import com.example.MokshaMarg.util.CloudinaryUploader;
import com.example.MokshaMarg.util.EmailDetails;
import com.example.MokshaMarg.util.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestaurentServiceImpl implements RestaurantService {
	
	@Autowired
	private CloudinaryUploader cloudinaryUploader;
	
	@Autowired
	private MailService mailService;

	@Override
	public AbstractApiResponse registerRestaurant(String restaurantjson, MultipartFile imageFile) {

		// Convert JSON string to Restaurant object
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Restaurant restaurant = objectMapper.readValue(restaurantjson, Restaurant.class);
//			System.out.println(imageFile.getOriginalFilename());
			Map<String,String> resp= cloudinaryUploader.uploadFile(imageFile);
			System.out.println(resp.get("url"));
			mailService.sendSimpleMail(new EmailDetails("nareshgupta0899@gmail.com","testing","test", "" ));
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant getRestaurantById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractApiResponse updateRestaurant(Long id, Restaurant updatedRestaurant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractApiResponse deleteRestaurant(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AbstractApiResponse> updateRestaurentUpdateStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
