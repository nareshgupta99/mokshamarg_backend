package com.example.MokshaMarg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.TempleResponse;
import com.example.MokshaMarg.service.TempleService;

@RestController
@RequestMapping("/api/v1/temple")
public class TempleController {

	@Autowired
	TempleService templeService;

	@PostMapping(value = "/add", consumes = "multipart/form-data")
	public ResponseEntity<AbstractApiResponse<TempleResponse>> addTemple(@RequestPart("temple") String temple,
			@RequestPart(value = "image", required = false) MultipartFile imageFile) {
		AbstractApiResponse<TempleResponse> templeResponse = templeService.addTemple(temple, imageFile);
		return new ResponseEntity<AbstractApiResponse<TempleResponse>>(templeResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AbstractApiResponse<TempleResponse>> delete(@PathVariable Long id) {
		AbstractApiResponse<TempleResponse> templeResponse = templeService.delete(id);
		return new ResponseEntity<AbstractApiResponse<TempleResponse>>(templeResponse, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<AbstractApiResponse<List<TempleResponse>>> getAllTemple() {
		AbstractApiResponse<List<TempleResponse>> templeResponse = templeService.getAllTemple();
		return new ResponseEntity<AbstractApiResponse<List<TempleResponse>>>(templeResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AbstractApiResponse<TempleResponse>> getTempleById(@PathVariable Long id) {
		AbstractApiResponse<TempleResponse> templeResponse = templeService.getTempleById(id);
		return new ResponseEntity<AbstractApiResponse<TempleResponse>>(templeResponse, HttpStatus.OK);
	}
}
