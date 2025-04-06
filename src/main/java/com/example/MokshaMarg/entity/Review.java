package com.example.MokshaMarg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reviewId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Long entityId; // Can be Guide, Restaurant, or Hotel
	private String entityType; // 'Guide', 'Restaurant', 'Hotel'

	private Integer rating;
	private String reviewText;
	private String createdAt;
}
