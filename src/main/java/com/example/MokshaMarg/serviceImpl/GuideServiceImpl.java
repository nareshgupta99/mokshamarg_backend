package com.example.MokshaMarg.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MokshaMarg.entity.Guide;
import com.example.MokshaMarg.repository.GuideRepository;
import com.example.MokshaMarg.repository.LanguageRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.GuideResponse;
import com.example.MokshaMarg.service.GuideService;

@Service
public class GuideServiceImpl implements GuideService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GuideRepository guideRepository;
	
	@Autowired 
	private LanguageRepository languageRepository;

	@Override
	public AbstractApiResponse<GuideResponse> createGuide(Guide guideRequest) {
		
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<GuideResponse> getGuideById(Long id) {
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<GuideResponse> updateGuide(Long id, Guide guideRequest) {
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<GuideResponse> deleteGuide(Long id) {
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<List<GuideResponse>> getAllGuides() {
		
		
		return null;
	}

	@Override
	public AbstractApiResponse<GuideResponse> updateStatus(Long id) {
		
		return null;
	}
	
	

}
