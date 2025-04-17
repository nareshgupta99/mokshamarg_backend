package com.example.MokshaMarg.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.dto.TempleDto;
import com.example.MokshaMarg.entity.Temple;
import com.example.MokshaMarg.repository.TempleRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.TempleResponse;
import com.example.MokshaMarg.service.TempleService;
import com.example.MokshaMarg.util.CloudinaryUploader;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class TempleServiceImpl implements TempleService {

	@Autowired
	private TempleRepository templeRepository;

	@Autowired
	private CloudinaryUploader cloudinaryUploader;

	@Override
	@Transactional
	public AbstractApiResponse<TempleResponse> addTemple(String templeJson, MultipartFile imageFile) {

		AbstractApiResponse<TempleResponse> abstractResponse = null;
		Temple temple = new Temple();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			TempleDto templeDto = objectMapper.readValue(templeJson, TempleDto.class);
			@SuppressWarnings("unchecked")
			Map<String, String> resp = cloudinaryUploader.uploadFile(imageFile);
			temple.setCity(templeDto.getCity());
			temple.setLivelink(templeDto.getLiveLink());
			temple.setName(templeDto.getName());
			temple.setImage(resp.get("url"));
			temple.setPublicId(resp.get("public_id"));
			temple.setStreetAddress(templeDto.getStreetAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Temple savedTemple = templeRepository.save(temple);
		TempleResponse templeResponse = new TempleResponse();
		templeResponse.setCity(savedTemple.getCity());
		templeResponse.setId(savedTemple.getId());
		templeResponse.setImage(savedTemple.getImage());
		templeResponse.setLivelink(savedTemple.getLivelink());
		templeResponse.setName(savedTemple.getName());
		templeResponse.setStreetAddress(savedTemple.getStreetAddress());
		abstractResponse = new AbstractApiResponse<TempleResponse>(true, "temple added", templeResponse);
		return abstractResponse;
	}

	@Override
	public AbstractApiResponse<TempleResponse> delete(Long templeId) {
		Temple existingTemple = templeRepository.findById(templeId)
				.orElseThrow(() -> new RuntimeException("Resourse not found"));
		TempleResponse templeResponse = new TempleResponse();
		templeResponse.setCity(existingTemple.getCity());
		templeResponse.setId(existingTemple.getId());
		templeResponse.setImage(existingTemple.getImage());
		templeResponse.setLivelink(existingTemple.getLivelink());
		templeResponse.setName(existingTemple.getName());
		templeResponse.setStreetAddress(existingTemple.getStreetAddress());
		AbstractApiResponse<TempleResponse> abstractResponse = new AbstractApiResponse<TempleResponse>(true,
				"temple added", templeResponse);
		return abstractResponse;
	}

	@Override
	public AbstractApiResponse<List<TempleResponse>> getAllTemple() {

		List<Temple> temples = templeRepository.findAll();
		List<TempleResponse> responses = temples.stream().map((existingTemple) -> {
			TempleResponse templeResponse = new TempleResponse();
			templeResponse.setCity(existingTemple.getCity());
			templeResponse.setId(existingTemple.getId());
			templeResponse.setImage(existingTemple.getImage());
			templeResponse.setLivelink(existingTemple.getLivelink());
			templeResponse.setName(existingTemple.getName());
			templeResponse.setStreetAddress(existingTemple.getStreetAddress());
			return templeResponse;
		}).toList();
		AbstractApiResponse<List<TempleResponse>> abstractResponse = new AbstractApiResponse<List<TempleResponse>>(true,
				"temple added", responses);
		return abstractResponse;
	}

	@Override
	public AbstractApiResponse<TempleResponse> getTempleById(Long id) {
		Temple existingTemple = templeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("temple not found"));
		TempleResponse templeResponse = new TempleResponse();
		templeResponse.setCity(existingTemple.getCity());
		templeResponse.setId(existingTemple.getId());
		templeResponse.setImage(existingTemple.getImage());
		templeResponse.setLivelink(existingTemple.getLivelink());
		templeResponse.setName(existingTemple.getName());
		templeResponse.setStreetAddress(existingTemple.getStreetAddress());
		AbstractApiResponse<TempleResponse> abstractResponse = new AbstractApiResponse<TempleResponse>(true,
				"temple added", templeResponse);
		return abstractResponse;
	}

}
