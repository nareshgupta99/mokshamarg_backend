package com.example.MokshaMarg.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MokshaMarg.service.PaymentService;
import com.razorpay.Order;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
	

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody Map<String, Object> payload) {
        try {
        	System.out.println("razorpay key =");
            int amount = (int) 500;
            String receipt = "hdhfh";
            String module = "food";// hotel/guide/food
            System.out.println("razorpay key = 2");

            Order order = paymentService.createOrder(amount, receipt, module);
            System.out.println("razorpay key = 3");
            return ResponseEntity.ok(order.toString());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating payment: " + e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> data) {
        try {
            boolean isValid = paymentService.verifySignature(
                data.get("razorpay_order_id"),
                data.get("razorpay_payment_id"),
                data.get("razorpay_signature")
            );

            return isValid ? ResponseEntity.ok("Payment verified") :
                ResponseEntity.status(400).body("Invalid signature");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Verification failed: " + e.getMessage());
        }
    }

}
