package com.example.MokshaMarg.service;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.annotation.PostConstruct;

@Service
public class PaymentService {

	
    private RazorpayClient client;
	
	@Value("${razorpay_key}")
	private String razorpayKey;
	
	@Value("${razorpay_secret}")
	private String razorpaySecret;
	
	
    @PostConstruct
    public void init() throws RazorpayException {
    	
        client = new RazorpayClient(razorpayKey, razorpaySecret);
    }

    public Order createOrder(int amountInPaise, String receiptId, String notes) throws RazorpayException {
    	System.out.println("razorpay key ="+razorpayKey);
        JSONObject options = new JSONObject();
        options.put("amount", amountInPaise);
        options.put("currency", "INR");
        options.put("receipt", receiptId);
        options.put("payment_capture", true);
        System.out.println("razorpay key = 66"+razorpayKey );
        // Optional: add notes to differentiate module (e.g., "module:hotel")
        JSONObject notesJson = new JSONObject();
        System.out.println("razorpay key = 999"+razorpayKey);
        notesJson.put("module", notes);
        options.put("notes", notesJson);
        System.out.println("razorpay key = vgfgffffdfd"+razorpayKey);
        return client.orders.create(options);
    }

    public boolean verifySignature(String orderId, String paymentId, String signature) throws Exception {
        String secret = "YourSecret";
        String payload = orderId + "|" + paymentId;

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        byte[] hash = sha256_HMAC.doFinal(payload.getBytes());
        String generatedSignature = new String(Hex.encodeHex(hash));

        return generatedSignature.equals(signature);
    }
}
