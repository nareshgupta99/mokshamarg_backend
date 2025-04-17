package com.example.MokshaMarg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MokshaMarg.entity.FoodType;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType,Long > {
	
	Optional<FoodType> findByName(String name); 

}
