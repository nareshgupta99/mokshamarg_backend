package com.example.MokshaMarg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MokshaMarg.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, String> {
	

}
