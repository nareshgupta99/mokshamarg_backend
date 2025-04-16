package com.example.MokshaMarg.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long restaurantId;

	private String name;
	private String address;
	private boolean open;
	private String openingTime;
	private String closeTime;
	private String latitude;
	private String longitude;
	private String image;
	private String publicId;
	private String startingPrice;

	@OneToOne
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference("restaurant_dishes")
	private List<Dish> dishes;

	
	@ManyToMany
	@JoinTable(name = "restaurant_food_types", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "food_type_id"))
	@JsonIgnore
	private List<FoodType> foodTypes;

}
