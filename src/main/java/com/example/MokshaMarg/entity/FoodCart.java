package com.example.MokshaMarg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FoodCart {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
	
//	 @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//	  private List<Dish> cartItems = new ArrayList<>();

}
