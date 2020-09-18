package com.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.entity.Cart;
import com.shoppingcart.entity.Item;
import com.shoppingcart.service.ShoppingCartService;

@RestController
@RequestMapping(value = "users")
public class CartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	// Get cart
	@GetMapping(value = "/{userId}/cart")
	public ResponseEntity<?> getCart(@PathVariable int userId){
		try {
			shoppingCartService.checkUser(userId);
			return new ResponseEntity<Cart>(shoppingCartService.getCart(userId),HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}

	// Display products in cart
	@GetMapping(value = "{userId}/cart/products")
	public ResponseEntity<?> getProductsInCart(@PathVariable("userId") int userId){
		try {
			shoppingCartService.checkUser(userId);
			List<Item> itemList = shoppingCartService.getProductsInCart(userId);
			return new ResponseEntity<List<Item>>(itemList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Add product in cart
	@PostMapping(value = "/{userId}/cart/products")
	public ResponseEntity<?> addProductInCart(
			@PathVariable("userId") int userId, 
			@RequestParam int productId){
		try {
			shoppingCartService.checkUser(userId);
			shoppingCartService.checkProduct(productId);
			Cart cart = shoppingCartService.addProductInCart(userId, productId);
			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Update Quantity of a Product in cart
	@PutMapping(value = "/{userId}/cart/products/{productId}")
	public ResponseEntity<?> updateProductQuantityInCart(
			@PathVariable("userId") int userId,
			@PathVariable("productId") int productId,
			@RequestParam int quantity){
		try {
			shoppingCartService.checkUser(userId);
			shoppingCartService.checkProduct(productId);
			Cart cart = shoppingCartService.updateProductQuantityInCart(userId, productId, quantity);
			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Delete Product from cart
	@DeleteMapping(value = "/{userId}/cart/products")
	public ResponseEntity<?> removeProductFromCart(
			@PathVariable("userId") int userId,
			@RequestParam int productId){
		try {
			shoppingCartService.checkUser(userId);
			shoppingCartService.checkProduct(productId);
			Cart cart = shoppingCartService.removeProductFromCart(userId, productId);
			return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}	
	}
	@DeleteMapping(value = "/{userId}/cart")
	public ResponseEntity<?> removeAllProductsFromCart(@PathVariable("userId") int userId){
		try {
			shoppingCartService.checkUser(userId);
			Cart cart = shoppingCartService.removeAllProductsFromCart(userId);
			return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
