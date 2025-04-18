package com.example.MokshaMarg.service;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.TempleResponse;

public interface TempleService {
	
	public AbstractApiResponse<TempleResponse> addTemple(String temple, MultipartFile imageFile);
	
	public AbstractApiResponse<TempleResponse> delete(Long templeId);
	
	public AbstractApiResponse<List<TempleResponse>> getAllTemple();
	
	public AbstractApiResponse<TempleResponse> getTempleById(Long id);

	
	

}
