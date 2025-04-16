package com.example.MokshaMarg.service;

import java.util.List;

import com.example.MokshaMarg.dto.GuideDto;
import com.example.MokshaMarg.entity.Guide;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.GuideResponse;

public interface GuideService {

    AbstractApiResponse<GuideResponse> createGuide(GuideDto guideRequest);

    AbstractApiResponse<GuideResponse> getGuideById(Long id);

    AbstractApiResponse<GuideResponse> updateGuide(Long id, Guide guideRequest);

    AbstractApiResponse<GuideResponse> deleteGuide(Long id);

    AbstractApiResponse<List<GuideResponse>> getAllGuides();
    
    AbstractApiResponse<GuideResponse> updateStatus(Long id);
}
