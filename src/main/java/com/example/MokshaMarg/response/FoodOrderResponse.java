package com.example.MokshaMarg.response;

import java.time.LocalDateTime;
import java.util.List;

import com.example.MokshaMarg.entity.CartItem;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.util.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodOrderResponse {
	private Long foodOrderId;

    private LocalDateTime orderDate;

    private String restaurantName;

    private String method; 
    
    private String razorpayOrderId;
    
    private double amount;
    
    private String currency;
    
    private String razorpayRecipt;
    
    private String userEmail;
}
