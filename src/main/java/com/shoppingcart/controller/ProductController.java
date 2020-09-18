package com.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.entity.Product;
import com.shoppingcart.exception.NoSuchProductException;
import com.shoppingcart.service.ShoppingCartService;

@RestController
public class ProductController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	// Display all products
	@GetMapping(value = "products")
	public ResponseEntity<?> getAllProducts() {
		try {
			return new ResponseEntity<List<Product>>(shoppingCartService.getAllProducts(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping(value = "products/searchById")
	public ResponseEntity<?> searchProductById(@RequestParam int productId) {
		try {
			Product product = shoppingCartService.searchProductByProductId(productId);
			return new ResponseEntity<Product>(product, HttpStatus.FOUND);
		} catch (NoSuchProductException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "products/searchByName")
	public ResponseEntity<?> searchProductByName(@RequestParam String productName) {
		try {
			Product product = shoppingCartService.searchProductByProductName(productName);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "products/searchByCategory")
	public ResponseEntity<?> searchProductByCategory(@RequestParam String category) {
		try {
			List<Product> productList = shoppingCartService.searchProductsByCategory(category);
			return new ResponseEntity<List<Product>>(productList, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
