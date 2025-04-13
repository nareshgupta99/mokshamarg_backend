package com.example.MokshaMarg.entity;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
