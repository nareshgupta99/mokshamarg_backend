package com.example.MokshaMarg.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.MokshaMarg.dto.FoodOrderDto;
import com.example.MokshaMarg.entity.CartItem;
import com.example.MokshaMarg.entity.FoodOrder;
import com.example.MokshaMarg.entity.Restaurant;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.repository.FoodOrderRepository;
import com.example.MokshaMarg.repository.UserRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.FoodOrderResponse;
import com.example.MokshaMarg.service.FoodOrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.transaction.Transactional;

@Service
public class FoodOrderServiceImpl implements FoodOrderService{

	@Value("${razorpay_key}")
	private String razorpayKey;

	@Value("${razorpay_secret}")
	private String razorpaySecret;

	@Autowired
	private FoodOrderRepository foodOrderRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Transactional
	public AbstractApiResponse<FoodOrderResponse> createOrder(FoodOrderDto  foodOrderDto) {
		Order order = null;
		double amount = foodOrderDto.getAmount();
		FoodOrder foodOrder = new FoodOrder();
		User user=null;
		
		try {
			RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount * 100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");
			order = razorpayClient.orders.create(orderRequest);
			String email =  SecurityContextHolder.getContext().getAuthentication().getName();
			 user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException());
			double amt = ((Number) order.get("amount")).doubleValue();
			
			
			foodOrder.setTotalAmount(amt / 100);
			foodOrder.setOrderDate(LocalDateTime.now());
			foodOrder.setUser(user);
			foodOrder.setRazorpayOrderId(order.get("id"));  
			foodOrder.setRazorpayRecipt(order.get("receipt"));
////			foodOrder.setPaymentStatus(order.get("status") == "Pen");
//			
			List<CartItem> cartItems = user.getCart().getCartItems();
			List<CartItem> orderItems = new ArrayList<>();

			for (CartItem cartItem : cartItems) {
			    CartItem item = new CartItem();
			    item.setDish(cartItem.getDish());
			    item.setDishNameSnapshot(cartItem.getDishNameSnapshot());
			    item.setDishPriceSnapshot(cartItem.getDishPriceSnapshot());
			    item.setQuantity(cartItem.getQuantity());
			    item.setFoodCart(null);
			    orderItems.add(item);
			}
			foodOrder.setItems(orderItems);
			

			List<Restaurant> restaurantsSet =new ArrayList<>();		
			for (CartItem item : cartItems) {
			 Restaurant restaurant	= item.getDish().getRestaurant();
			 restaurantsSet.add(restaurant);
			}			
			foodOrder.setRestaurant(restaurantsSet);
		} catch (RazorpayException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		foodOrderRepo.save(foodOrder);
	 FoodOrderResponse response	= new FoodOrderResponse();
	 response.setFoodOrderId(foodOrder.getFoodOrderId());
	 response.setOrderDate(foodOrder.getOrderDate());
	 response.setAmount(foodOrder.getTotalAmount());
	 response.setRazorpayOrderId(foodOrder.getRazorpayOrderId());
	 response.setCurrency("INR");
	 response.setUserEmail(user.getEmail());
		return new AbstractApiResponse<FoodOrderResponse>(true, "order created", response);

	}
	
	
	
//	@Transactional
//	public boolean paymentVerification(PaymentVerificationDto paymentVerificationDto) throws RazorpayException, PaymentException {
//
//		System.out.println("payment verification done"+paymentVerificationDto);
//		
//		String order_id= paymentVerificationDto.getOrderId();
//		String payment_id= paymentVerificationDto.getPaymentId();
//		String signature=paymentVerificationDto.getSignature();
//		
//		JSONObject options = new JSONObject();
//		options.put("razorpay_order_id", order_id);
//		options.put("razorpay_payment_id",payment_id);
//		options.put("razorpay_signature", signature);
//
//		boolean match = Utils.verifyPaymentSignature(options, razorpaySecret);
//		if (!match) {
//			throw new PaymentException("wrong payment method or something went wrong please contact custumer support team");
//		}
//		Orders purchaseOrder = this.purchaseOrderRepo.findByOrderId(order_id);
//		purchaseOrder.setPaymentTransactionId(payment_id);
//		purchaseOrder.setPaymentStatus("paid");
//		purchaseOrder.setSignature(signature);
//		purchaseOrder.setPurchaseDate(new Date());
//		
//		
//		
//		Orders purchaseOrder1=	purchaseOrderRepo.save(purchaseOrder);
//		
//		
//			myCourseService.savePurachsedCourse(paymentVerificationDto.getCoursesId(),purchaseOrder1);
//			
//		this.soldByService.saveSoldBy();
//		
//		return true;
//	}


}
