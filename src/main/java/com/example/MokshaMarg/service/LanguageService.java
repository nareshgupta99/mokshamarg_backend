package com.example.MokshaMarg.service;

import java.util.List;

import com.example.MokshaMarg.entity.Language;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.LanguageResponse;

public interface LanguageService {

    AbstractApiResponse<LanguageResponse> createLanguage(Language request);

    AbstractApiResponse<LanguageResponse> getLanguageById(Long id);

    AbstractApiResponse<List<LanguageResponse>> getAllLanguages();
}
