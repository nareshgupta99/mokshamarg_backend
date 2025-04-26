
package com.example.MokshaMarg.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodCartResponse {
    private String cartItemId;
    private String itemName;
    private int quantity;
    private double price;
    private String image;
    private String foodType;
    private String dishId;


 
}
