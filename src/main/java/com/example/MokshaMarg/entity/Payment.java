
package com.example.MokshaMarg.entity;

import java.time.LocalDateTime;

import com.example.MokshaMarg.util.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private LocalDateTime paymentDate;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String method; 

    @OneToOne
    @JoinColumn(name = "order_id")
    private FoodOrder order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
