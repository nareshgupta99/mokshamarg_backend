package com.example.MokshaMarg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentVerificationDto {

	private String orderId;

	private String paymentId;

	private String signature;
	
	private String razorPayOrderId;

	

}
