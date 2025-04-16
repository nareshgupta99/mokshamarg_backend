package com.example.MokshaMarg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MokshaMarg.entity.FoodCart;
import com.example.MokshaMarg.entity.User;


@Repository
public interface FoodCartRepository extends JpaRepository<FoodCart, Long> {
	
	Optional<FoodCart> findByUser(User user);

}
