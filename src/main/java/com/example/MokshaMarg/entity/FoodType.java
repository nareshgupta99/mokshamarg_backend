package com.example.MokshaMarg.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.ToString;

@Entity
@ToString
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodTypeId;

    private String name;
    
//    @ManyToMany(mappedBy = "foodTypes")
//    @JsonIgnore
//    private List<Restaurant> restaurant;
    
//    @OneToMany(mappedBy= "foodTypes")
//    @JsonBackReference("dish_foodTypes")
//    private List<Dish> dishs;

	public Long getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(Long foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
    
    
}