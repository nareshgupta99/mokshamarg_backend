package com.example.MokshaMarg.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data

public class Dish {

	@Id
	private String dishId;

	private String dishName;
	
	private String image;
	
	private String publicId;
	
	private double price;
	
	private String description;
	
	private String shortDescription;

	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	@JsonBackReference("restaurant_dishes")
	private Restaurant restaurant;
	
	
	@ManyToOne
	@JsonIgnore
	@JsonManagedReference("dish_foodTypes")
	private FoodType foodTypes;
	

}
