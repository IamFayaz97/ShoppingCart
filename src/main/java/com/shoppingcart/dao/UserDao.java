package com.shoppingcart.dao;

import java.util.List;

import com.shoppingcart.entity.User;
import com.shoppingcart.exception.NoSuchUserException;

public interface UserDao {
	
	List<User> getAllUsers();
	User getUser(int userId) throws NoSuchUserException;
	User addUser(String userName);
	User updateUser(int userId, String userName) throws NoSuchUserException;
	void deleteUser(int userid) throws NoSuchUserException;
	
	void checkUser(int userId) throws NoSuchUserException;

}
