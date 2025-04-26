package com.example.MokshaMarg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.entity.User;

@Repository
public interface RestaurentRepository extends JpaRepository<Restaurant, Long> {

	Optional<Restaurant> findByUser(User user);
	
}
