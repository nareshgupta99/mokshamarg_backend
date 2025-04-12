package com.example.MokshaMarg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {

	private String dishName;

	private String image;

	private String publicId;

	private double price;

	private String restaurantId;

	private Long foodTypeId;

}
