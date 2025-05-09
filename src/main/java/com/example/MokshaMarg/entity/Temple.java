package com.example.MokshaMarg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor	
@Data
public class Temple {
	
	@Id
	private String id;
	
	private String name;
	
	private String streetAddress;
	
	private String city;
	
	private String Livelink;
	
	private String image;
	
	private String publicId;
	
}
