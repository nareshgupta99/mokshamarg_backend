
package com.example.MokshaMarg.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodCartResponse {
    private Long cartId;
    private String itemName;
    private int quantity;
    private double price;


 
}
