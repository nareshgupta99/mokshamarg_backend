package com.example.MokshaMarg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MokshaMarg.entity.Temple;

@Repository
public interface TempleRepository extends JpaRepository<Temple,Long> {

}
