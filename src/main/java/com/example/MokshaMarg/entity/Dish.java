package com.example.MokshaMarg.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dishId;

	private String dishName;
	private double price;

	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	@ManyToMany
	@JoinTable(
	    name = "dish_food_types",
	    joinColumns = @JoinColumn(name = "dish_id"),
	    inverseJoinColumns = @JoinColumn(name = "food_type_id")
	)
	private List<FoodType> foodTypes;

}
