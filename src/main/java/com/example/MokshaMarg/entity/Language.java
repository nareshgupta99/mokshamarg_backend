package com.example.MokshaMarg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Language {
	

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String languageId;

    private String languageName;
    private String createdAt;

}
