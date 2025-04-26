package com.example.MokshaMarg.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MokshaMarg.entity.Language;
import com.example.MokshaMarg.exception.ResourceNotFoundExcepton;
import com.example.MokshaMarg.repository.LanguageRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.LanguageResponse;
import com.example.MokshaMarg.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public AbstractApiResponse<LanguageResponse> createLanguage(Language request) {
	
		request.setLanguageId(UUID.randomUUID().toString());
		Language savedLanguage = languageRepository.save(request);
		LanguageResponse languageResponse = modelMapper.map(savedLanguage, LanguageResponse.class);

		AbstractApiResponse<LanguageResponse> abstractResponse = new AbstractApiResponse<LanguageResponse>(true,
				"language created", languageResponse);
		return abstractResponse;
	}

	@Override
	public AbstractApiResponse<LanguageResponse> getLanguageById(String id) {

		Language savedLanguage = languageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExcepton("id", "requested language not present"));

		LanguageResponse languageResponse = modelMapper.map(savedLanguage, LanguageResponse.class);

		AbstractApiResponse<LanguageResponse> abstractResponse = new AbstractApiResponse<LanguageResponse>(true,
				"language created", languageResponse);
		return abstractResponse;
	}


	@Override
	public AbstractApiResponse<List<LanguageResponse>> getAllLanguages() {
		List<Language> language= languageRepository.findAll();
		List<LanguageResponse> languageResponse=	language.stream().map((lang)->modelMapper.map(lang, LanguageResponse.class)).toList();
		
		AbstractApiResponse<List<LanguageResponse>> abstractResponse = new AbstractApiResponse<List<LanguageResponse>>(true,
				"language created", languageResponse);
		return abstractResponse;
	}

}
