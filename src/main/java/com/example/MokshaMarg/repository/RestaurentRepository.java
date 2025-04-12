package com.example.MokshaMarg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MokshaMarg.entity.Restaurant;

@Repository
public interface RestaurentRepository extends JpaRepository<Restaurant, Long> {

}
