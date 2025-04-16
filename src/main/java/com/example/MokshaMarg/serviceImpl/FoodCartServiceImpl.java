package com.example.MokshaMarg.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.MokshaMarg.dto.CartItemDto;
import com.example.MokshaMarg.entity.CartItem;
import com.example.MokshaMarg.entity.Dish;
import com.example.MokshaMarg.entity.FoodCart;
import com.example.MokshaMarg.entity.User;
import com.example.MokshaMarg.exception.ResourceNotFoundExcepton;
import com.example.MokshaMarg.repository.CartItemRepository;
import com.example.MokshaMarg.repository.DishRepository;
import com.example.MokshaMarg.repository.FoodCartRepository;
import com.example.MokshaMarg.repository.UserRepository;
import com.example.MokshaMarg.response.AbstractApiResponse;
import com.example.MokshaMarg.response.FoodCartResponse;
import com.example.MokshaMarg.service.FoodCartService;

import jakarta.transaction.Transactional;

public class FoodCartServiceImpl implements FoodCartService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FoodCartRepository foodCartRepo;
	
	@Autowired
	private DishRepository dishRepository;
	
	@Autowired 
	private CartItemRepository cartItemRepository;
	
	@Transactional
	@Override
	public AbstractApiResponse<FoodCartResponse> addItemToCart(CartItemDto cartItemDto) {
		
		String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User existingUser = userRepo.findByEmail(userEmail).orElseThrow(()->new ResourceNotFoundExcepton(userEmail,"not found Something Wrong failed to add item into cart"));
		
		FoodCart foodCart = foodCartRepo.findByUser(existingUser).orElseGet(()->{
			FoodCart cart=new FoodCart();
			cart.setUser(existingUser);
			return foodCartRepo.save(cart);
		});
		
		
		 Dish dish = dishRepository.findById(cartItemDto.getDishId())
			        .orElseThrow(() -> new RuntimeException("Dish not found"));
		
		 Optional<CartItem> existingItemOpt = foodCart.getCartItems().stream()
			        .filter(item -> item.getDish().getDishId().equals(cartItemDto.getDishId()))
			        .findFirst();
		 
		 CartItem cartItem;

		    if (existingItemOpt.isPresent()) {
		        cartItem = existingItemOpt.get();
		        cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity()); 
		    }  else {
		        cartItem = new CartItem();
		        cartItem.setFoodCart(foodCart);
		        cartItem.setDish(dish);
		        cartItem.setQuantity(cartItemDto.getQuantity());
		        cartItem.setDishNameSnapshot(dish.getDishName());
		        cartItem.setDishPriceSnapshot(dish.getPrice());
		     CartItem savedCartItem =  cartItemRepository.save(cartItem);

		     	foodCart. getCartItems().add(savedCartItem); 
		    }
		    foodCartRepo.save(foodCart);
		 
		return null;
	}

	@Override
	public AbstractApiResponse<FoodCartResponse> getCartById(Long cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public AbstractApiResponse<FoodCartResponse> removeFromcart(Long cartItemId) {
		
		String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User existingUser = userRepo.findByEmail(userEmail).orElseThrow(()->new ResourceNotFoundExcepton(userEmail,"not found Something Wrong failed to remove item from cart"));
		  CartItem cartItem = cartItemRepository.findById(cartItemId)
		            .orElseThrow(() -> new ResourceNotFoundExcepton("CartItem", "ID"));

		    if (!cartItem.getFoodCart().getUser().getUserId().equals(existingUser.getUserId())) {
		        throw new RuntimeException("You do not have permission to delete this item.");
		    }

		    cartItemRepository.delete(cartItem);
		 return null;
	}

	@Override
	public AbstractApiResponse<List<FoodCartResponse>> getAllItemFromCart() {
		
		String email =SecurityContextHolder.getContext().getAuthentication().getName();
		User existingUser = userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundExcepton(email,"not found Something Wrong failed to remove item from cart"));
		FoodCart foodCart = foodCartRepo.findByUser(existingUser).orElseThrow();
		List< CartItem>  cartItems= foodCart.getCartItems();
		return null;
	}

	@Override
	public AbstractApiResponse<List<FoodCartResponse>> removeAllFromCart() {
		
		String email =SecurityContextHolder.getContext().getAuthentication().getName();
		User existingUser = userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundExcepton(email,"not found Something Wrong failed to remove item from cart"));
		FoodCart foodCart = foodCartRepo.findByUser(existingUser).orElseThrow();
		foodCart.getCartItems().clear();

	    // Save the cart after clearing the list
	    foodCartRepo.save(foodCart);

		return null;
	}

}
