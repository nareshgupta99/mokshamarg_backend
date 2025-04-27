package com.example.MokshaMarg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MokshaMarg.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String>{
	
	Optional<Language> findByLanguageName(String name);
}
