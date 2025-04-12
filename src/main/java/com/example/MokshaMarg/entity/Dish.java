package com.example.MokshaMarg.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;


@Entity
@Data

public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dishId;

	private String dishName;
	
	private String image;
	
	private String publicId;
	
	private double price;

	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	@JsonIgnore
	private Restaurant restaurant;
	
	
	@ManyToOne
	@JsonIgnore
	private FoodType foodTypes;

}
