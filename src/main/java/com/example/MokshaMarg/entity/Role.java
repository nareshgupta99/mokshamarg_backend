package com.example.MokshaMarg.entity;

import com.example.MokshaMarg.util.RoleName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class Role {
	

	private String roleId;
	
	@Enumerated(EnumType.STRING)
    private RoleName roleName;
}
