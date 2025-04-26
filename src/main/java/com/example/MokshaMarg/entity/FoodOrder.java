package com.example.MokshaMarg.entity;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodOrder {

    @Id
    private String foodOrderId;

    private LocalDateTime orderDate;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Restaurant> restaurant;

    @OneToMany( cascade = CascadeType.ALL)
    private List<CartItem> items;
    
    private String paymentStatus;

    private String method; 
    
    private String razorpayOrderId;
    
    private String razorpayRecipt;
    
    private String paymentId;
    
}
