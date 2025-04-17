package com.example.MokshaMarg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVerificationDto {

	private Long orderId;

	private String paymentId;

	private String signature;

	@Override
	public String toString() {
		return "PaymentVerificationDto [orderId=" + orderId + ", paymentId=" + paymentId + ", signature=" + signature
				+ ", courseId=" + "]";
	}

}
