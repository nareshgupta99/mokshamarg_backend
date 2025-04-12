

package com.example.MokshaMarg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MokshaMarg.entity.Language;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.LanguageResponse;
import com.example.MokshaMarg.service.LanguageService;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping
    public ResponseEntity<AbstractApiResponse<LanguageResponse>> createLanguage(@RequestBody Language request) {
        AbstractApiResponse<LanguageResponse> response = languageService.createLanguage(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbstractApiResponse<LanguageResponse>> getLanguageById(@PathVariable Long id) {
        AbstractApiResponse<LanguageResponse> response = languageService.getLanguageById(id);
        return ResponseEntity.ok(response);
    }



    @GetMapping
    public ResponseEntity<AbstractApiResponse<List<LanguageResponse>>> getAllLanguages() {
        AbstractApiResponse<List<LanguageResponse>> response = languageService.getAllLanguages();
        return ResponseEntity.ok(response);
    }
}

