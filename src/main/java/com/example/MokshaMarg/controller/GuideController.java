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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.entity.Guide;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.GuideResponse;
import com.example.MokshaMarg.service.GuideService;

@RestController
@RequestMapping("/api/guides")
public class GuideController {

    @Autowired
    private GuideService guideService;

    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public ResponseEntity<AbstractApiResponse<GuideResponse>> createGuide(@RequestPart("guide") String guide,
    		@RequestPart(value = "image", required = false) MultipartFile imageFile) {
        AbstractApiResponse<GuideResponse> response = guideService.createGuide(guide,imageFile);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbstractApiResponse<GuideResponse>> getGuideById(@PathVariable String id) {
        AbstractApiResponse<GuideResponse> response = guideService.getGuideById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbstractApiResponse<GuideResponse>> updateGuide(@PathVariable String id, @RequestBody Guide guideRequest) {
        AbstractApiResponse<GuideResponse> response = guideService.updateGuide(id, guideRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AbstractApiResponse<GuideResponse>> deleteGuide(@PathVariable String id) {
        AbstractApiResponse<GuideResponse> response = guideService.deleteGuide(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<AbstractApiResponse<List<GuideResponse>>> getAllGuides() {
        AbstractApiResponse<List<GuideResponse>> response = guideService.getAllGuides();
        return ResponseEntity.ok(response);
    }
}
