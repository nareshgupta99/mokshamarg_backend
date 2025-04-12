package com.example.MokshaMarg.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private String status; // e.g., PENDING, CONFIRMED, CANCELLED

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
