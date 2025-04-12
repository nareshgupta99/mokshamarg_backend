package com.example.MokshaMarg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MokshaMarg.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>{

}
