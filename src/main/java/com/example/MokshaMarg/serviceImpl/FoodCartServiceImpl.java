package com.example.MokshaMarg.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

@Service
public class FoodCartServiceImpl implements FoodCartService {

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
		User existingUser = userRepo.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundExcepton(userEmail,
				"not found Something Wrong failed to add item into cart"));

		FoodCart foodCart = foodCartRepo.findByUser(existingUser).orElseGet(() -> {
			FoodCart cart = new FoodCart();
			cart.setUser(existingUser);
			return foodCartRepo.save(cart);
		});

		Dish dish = dishRepository.findById(cartItemDto.getDishId())
				.orElseThrow(() -> new RuntimeException("Dish not found"));

		Optional<CartItem> existingItemOpt = foodCart.getCartItems().stream()
				.filter(item -> item.getDish().getDishId().equals(cartItemDto.getDishId())).findFirst();

		CartItem cartItem;
		CartItem savedCartItem = null;

		if (existingItemOpt.isPresent()) {
			cartItem = existingItemOpt.get();
			cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
		} else {
			cartItem = new CartItem();
			cartItem.setFoodCart(foodCart);
			cartItem.setDish(dish);
			cartItem.setQuantity(cartItemDto.getQuantity());
			cartItem.setDishNameSnapshot(dish.getDishName());
			cartItem.setDishPriceSnapshot(dish.getPrice());
			savedCartItem = cartItemRepository.save(cartItem);

			foodCart.getCartItems().add(savedCartItem);
		}
		foodCartRepo.save(foodCart);
		FoodCartResponse response = new FoodCartResponse();

		if (existingItemOpt.isPresent()) {
			cartItem = existingItemOpt.get();
			response.setItemName(cartItem.getDishNameSnapshot());
			response.setPrice(cartItem.getDishPriceSnapshot());
			response.setCartItemId(cartItem.getCartItemId());
			response.setQuantity(cartItem.getQuantity());
			response.setImage(cartItem.getDish().getImage());
			response.setFoodType(cartItem.getDish().getFoodTypes().getName());
			response.setDishId(cartItem.getDish().getDishId());

		} else {

			response.setItemName(savedCartItem.getDishNameSnapshot());
			response.setPrice(savedCartItem.getDishPriceSnapshot());
			response.setCartItemId(savedCartItem.getCartItemId());
			response.setQuantity(savedCartItem.getQuantity());
			response.setImage(savedCartItem.getDish().getImage());
			response.setFoodType(savedCartItem.getDish().getFoodTypes().getName());
			response.setDishId(savedCartItem.getDish().getDishId());
		}

		return new AbstractApiResponse<FoodCartResponse>(true, "item added to the cart", response);
	}

	@Override
	public AbstractApiResponse<FoodCartResponse> getCartById(Long cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractApiResponse<FoodCartResponse> removeFromcart(Long cartItemId) {

		String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User existingUser = userRepo.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundExcepton(userEmail,
				"not found Something Wrong failed to remove item from cart"));
		CartItem cartItem = cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundExcepton("CartItem", "ID"));

		if (!cartItem.getFoodCart().getUser().getUserId().equals(existingUser.getUserId())) {
			throw new RuntimeException("You do not have permission to delete this item.");
		}

		cartItemRepository.delete(cartItem);
		FoodCartResponse response = new FoodCartResponse();
		response.setItemName(cartItem.getDishNameSnapshot());
		response.setPrice(cartItem.getDishPriceSnapshot());
		response.setCartItemId(cartItem.getCartItemId());
		response.setQuantity(cartItem.getQuantity());
		 return new AbstractApiResponse<FoodCartResponse>(true, "item removed from the cart", response);
	}

	@Override
	public AbstractApiResponse<List<FoodCartResponse>> getAllItemFromCart() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User existingUser = userRepo.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundExcepton(email, "not found Something Wrong failed to remove item from cart"));
		FoodCart foodCart = foodCartRepo.findByUser(existingUser).orElseThrow();
		List<CartItem> cartItems = foodCart.getCartItems();
		List<FoodCartResponse> foodCartResponses = cartItems.stream().map((item)->{
			FoodCartResponse cartResponse	= new FoodCartResponse();
			cartResponse.setItemName(item.getDishNameSnapshot());
			cartResponse.setPrice(item.getDishPriceSnapshot());
			cartResponse.setCartItemId(item.getCartItemId());
			cartResponse.setQuantity(item.getQuantity());
			cartResponse.setImage(item.getDish().getImage());
			cartResponse.setFoodType(item.getDish().getFoodTypes().getName());
			cartResponse.setDishId(item.getDish().getDishId());
			return cartResponse;
		}).toList();
		return new AbstractApiResponse<List<FoodCartResponse>>(true, "success", foodCartResponses);
		
	}

	@Override
	public AbstractApiResponse<List<FoodCartResponse>> removeAllFromCart() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User existingUser = userRepo.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundExcepton(email, "not found Something Wrong failed to remove item from cart"));
		FoodCart foodCart = foodCartRepo.findByUser(existingUser).orElseThrow();
		foodCart.getCartItems().clear();

		// Save the cart after clearing the list
		foodCartRepo.save(foodCart);

		return new AbstractApiResponse<List<FoodCartResponse>>(true, "success", new ArrayList<>(0));
	}
	
	@Override
	public AbstractApiResponse<FoodCartResponse> removeQuantityFromCart(Long id) {

		String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		User existingUser = userRepo.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundExcepton(userEmail,
				"not found Something Wrong failed to remove item from cart"));
		CartItem cartItem = cartItemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExcepton("CartItem", "ID"));

		if (!cartItem.getFoodCart().getUser().getUserId().equals(existingUser.getUserId())) {
			throw new RuntimeException("You do not have permission to delete this item.");
		}

		FoodCartResponse response = new FoodCartResponse();
		if(cartItem.getQuantity() == 1) {
			cartItemRepository.delete(cartItem);
			response.setQuantity(0);
		}else {
			cartItem.setQuantity(cartItem.getQuantity()-1);
			cartItemRepository.save(cartItem);
			response.setQuantity(cartItem.getQuantity());
		}
		response.setItemName(cartItem.getDishNameSnapshot());
		response.setPrice(cartItem.getDishPriceSnapshot());
		response.setCartItemId(cartItem.getCartItemId());
		 return new AbstractApiResponse<FoodCartResponse>(true, "item removed from the cart", response);
	}

}
