package com.example.MokshaMarg.entity;

import com.example.MokshaMarg.util.RoleName;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Role {
	

	private Long roleId;
	
	@Enumerated(EnumType.STRING)
    private RoleName roleName;
}
