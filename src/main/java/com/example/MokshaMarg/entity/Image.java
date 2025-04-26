
package com.example.MokshaMarg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Image {

	@Id
	private String imageId;

	private String url;
	private Long entityId; // Can refer to Dish, Restaurant, or Guide
	private String entityType; // 'Dish', 'Restaurant', 'Guide'
}
