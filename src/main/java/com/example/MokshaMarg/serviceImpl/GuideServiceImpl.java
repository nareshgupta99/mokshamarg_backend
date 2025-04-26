package com.example.MokshaMarg.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MokshaMarg.dto.GuideDto;
import com.example.MokshaMarg.entity.Guide;
import com.example.MokshaMarg.entity.Language;
import com.example.MokshaMarg.repository.GuideRepository;
import com.example.MokshaMarg.repository.LanguageRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.GuideResponse;
import com.example.MokshaMarg.service.GuideService;

@Service
public class GuideServiceImpl implements GuideService {
	
	
	@Autowired
	private GuideRepository guideRepository;
	
	@Autowired 
	private LanguageRepository languageRepository;

	@Override
	public AbstractApiResponse<GuideResponse> createGuide(GuideDto guideRequest) {
		
		List<Language> languages = guideRequest.getLanguages().stream().map((id)->languageRepository.findById(id).orElseThrow()).toList();
		Guide guide= new Guide();
		guide.setLanguages(languages);
		guide.setPrice(guideRequest.getPrice());
		guide.setGuideId(UUID.randomUUID().toString());
//		guide.setUser();
		
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<GuideResponse> getGuideById(String id) {
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<GuideResponse> updateGuide(String id, Guide guideRequest) {
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<GuideResponse> deleteGuide(String id) {
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<List<GuideResponse>> getAllGuides() {
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<GuideResponse> updateStatus(String id) {
		
		return null;
	}
	
	

}
