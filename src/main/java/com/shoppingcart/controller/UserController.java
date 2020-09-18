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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.entity.User;
import com.shoppingcart.service.ShoppingCartService;

@RestController
public class UserController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping(value="users")
	public ResponseEntity<?> getAllUsers(){
		try {
			return new ResponseEntity<List<User>>(shoppingCartService.getAllUsers(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value="users/{userId}")
	public ResponseEntity<?> getUser(@PathVariable int userId) {
		try {
			shoppingCartService.checkUser(userId);
			return new ResponseEntity<User>(shoppingCartService.getUser(userId),HttpStatus.FOUND);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping(value="users")
	public ResponseEntity<?> addUser(@RequestParam String userName) {
		try {
			return new ResponseEntity<User>(shoppingCartService.addUser(userName),HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping(value="users/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestParam String userName) {
		try {
			shoppingCartService.checkUser(userId);
		return new ResponseEntity<User>(shoppingCartService.updateUser(userId, userName), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping(value="users")
	public ResponseEntity<String> deleteUser(@RequestParam int userId){
		try {
			shoppingCartService.checkUser(userId);
			shoppingCartService.deleteUser(userId);
			return new ResponseEntity<String>("Successfully removed the User", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}


}
