package com.example.MokshaMarg.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantDto {

	private String name;
	private String address;
	private boolean open;
	private String openingTime;
	private String closeTime;
	private String latitude;
	private String longitude;
	private String publicId;
	private String startingPrice;
	private List<String> foodTypes = new ArrayList<>();
}
