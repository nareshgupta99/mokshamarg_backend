package com.example.MokshaMarg.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.dto.DishDto;
import com.example.MokshaMarg.dto.GuideDto;
import com.example.MokshaMarg.entity.Guide;
import com.example.MokshaMarg.entity.Language;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.exception.ResourceNotFoundExcepton;
import com.example.MokshaMarg.repository.GuideRepository;
import com.example.MokshaMarg.repository.LanguageRepository;
import com.example.MokshaMarg.repository.UserRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.DishResponse;
import com.example.MokshaMarg.response.GuideResponse;
import com.example.MokshaMarg.service.GuideService;
import com.example.MokshaMarg.util.CloudinaryUploader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GuideServiceImpl implements GuideService {

	@Autowired
	private GuideRepository guideRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CloudinaryUploader cloudinaryUploader;

	@Override
	public AbstractApiResponse<GuideResponse> createGuide(String guideJson, MultipartFile imageFile) {

		GuideResponse guideResponse = new GuideResponse();
		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
			GuideDto guideDto = objectMapper.readValue(guideJson, GuideDto.class);
			
			
			List<Language> languages = guideDto.getLanguages().stream()
					.map((id) -> languageRepository.findByLanguageName(id).orElseThrow()).toList();
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepo.findByEmail(email)
					.orElseThrow(() -> new ResourceNotFoundExcepton("user not found", email));
			Guide guide = new Guide();
			guide.setLanguages(languages);
			guide.setPrice(guideDto.getPrice());
			guide.setGuideId(UUID.randomUUID().toString());
			guide.setUser(user);
			guideRepository.save(guide);

			
			guideResponse.setLanguages(guide.getLanguages());
			guideResponse.setCreatedAt(guide.getCreatedAt());
			guideResponse.setImageUrl(guide.getImageUrl());
			guideResponse.setPrice(guide.getPrice());

		} catch (JsonProcessingException e) {
			throw new RuntimeException("Invalid restaurant JSON format: " + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("Image upload failed: " + e.getMessage());
		} catch (ResourceNotFoundExcepton e) {
			throw new RuntimeException("resource not found: " + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong during add dish: " + e.getMessage());
		}

		AbstractApiResponse<GuideResponse> abstractApiResponse = new AbstractApiResponse<>();
		abstractApiResponse.setData(guideResponse);
		return abstractApiResponse;
	}

	@Override
	public AbstractApiResponse<GuideResponse> getGuideById(String id) {

		Guide guide = guideRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExcepton("user not found", id));

		GuideResponse guideResponse = new GuideResponse();
		guideResponse.setLanguages(guide.getLanguages());
		guideResponse.setCreatedAt(guide.getCreatedAt());
		guideResponse.setImageUrl(guide.getImageUrl());
		guideResponse.setPrice(guide.getPrice());

		AbstractApiResponse<GuideResponse> abstractApiResponse = new AbstractApiResponse<>();
		abstractApiResponse.setData(guideResponse);
		return abstractApiResponse;
	}

	@Override
	public AbstractApiResponse<GuideResponse> updateGuide(String id, Guide guideRequest) {

		return null;
	}

	@Override
	public AbstractApiResponse<GuideResponse> deleteGuide(String id) {

		Guide guide = guideRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExcepton("user not found", id));

		GuideResponse guideResponse = new GuideResponse();
		guideResponse.setLanguages(guide.getLanguages());
		guideResponse.setCreatedAt(guide.getCreatedAt());
		guideResponse.setImageUrl(guide.getImageUrl());
		guideResponse.setPrice(guide.getPrice());

		guideRepository.delete(guide);

		AbstractApiResponse<GuideResponse> abstractApiResponse = new AbstractApiResponse<>();
		abstractApiResponse.setData(guideResponse);
		return abstractApiResponse;

	}

	@Override
	public AbstractApiResponse<List<GuideResponse>> getAllGuides() {

		List<Guide> guides = guideRepository.findAll();

		List<GuideResponse> guideResponses = guides.stream().map((guide) -> modelMapper.map(guide, GuideResponse.class))
				.toList();

		AbstractApiResponse<List<GuideResponse>> abstractApiResponse = new AbstractApiResponse<>();
		abstractApiResponse.setData(guideResponses);
		return abstractApiResponse;

	}

	@Override
	public AbstractApiResponse<GuideResponse> updateStatus(String id) {

		Guide guide = guideRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExcepton("user not found", id));

		return null;
	}

}
