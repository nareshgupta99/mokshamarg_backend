package com.example.MokshaMarg.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class FoodCart {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//	
//	 @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//	  private List<Dish> cartItems;

}
