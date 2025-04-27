package com.example.MokshaMarg.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.dto.GuideDto;
import com.example.MokshaMarg.entity.Guide;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.GuideResponse;

public interface GuideService {

    AbstractApiResponse<GuideResponse> createGuide(String guideJson,MultipartFile imageFile);

    AbstractApiResponse<GuideResponse> getGuideById(String id);

    AbstractApiResponse<GuideResponse> updateGuide(String id, Guide guideRequest);

    AbstractApiResponse<GuideResponse> deleteGuide(String id);

    AbstractApiResponse<List<GuideResponse>> getAllGuides();
    
    AbstractApiResponse<GuideResponse> updateStatus(String id);
}
