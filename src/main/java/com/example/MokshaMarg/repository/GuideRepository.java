package com.example.MokshaMarg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MokshaMarg.entity.Guide;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {

}
