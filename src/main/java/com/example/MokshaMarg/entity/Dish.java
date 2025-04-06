package com.example.MokshaMarg.entity;

import com.example.MokshaMarg.util.RoleName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
}
