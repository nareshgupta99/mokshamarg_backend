package com.example.MokshaMarg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Review {

	@Id
	private String reviewId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Long entityId; // Can be Guide, Restaurant, or Hotel
	private String entityType; // 'Guide', 'Restaurant', 'Hotel'

	private Integer rating =0;
	private String reviewText;
	private String createdAt;
}
