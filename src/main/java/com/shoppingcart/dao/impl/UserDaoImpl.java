package com.shoppingcart.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppingcart.dao.UserDao;
import com.shoppingcart.entity.User;
import com.shoppingcart.exception.NoSuchUserException;
import com.shoppingcart.repository.UserRepository;

@Component
public class UserDaoImpl implements UserDao{
	
	Logger logger = LoggerFactory.getLogger(CartDaoImpl.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO get all users
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public User getUser(int userId) throws NoSuchUserException {
		// TODO get a user
		User user = userRepository.findById(userId).get();
		logger.debug(user.toString());
		return user;
	}

	@Override
	public User addUser(String userName) {
		// TODO add a new User
		User user = new User();
		user.setUserName(userName);
		user = userRepository.save(user);
		logger.debug(user.toString());
		return user;
	}

	@Override
	public User updateUser(int userId, String userName) throws NoSuchUserException {
		// TODO update user Name
		User user = userRepository.findById(userId).get();
		user.setUserName(userName);
		logger.debug(user.toString());
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(int userId) throws NoSuchUserException {
		// TODO deleting user with userId
		userRepository.deleteById(userId);
		logger.debug("Successfully deleted user with userId: "+userId);
	}
	
	@Override
	public void checkUser(int userId) throws NoSuchUserException{
		if(!userRepository.findById(userId).isPresent()) {
			logger.error("No user with userId : "+userId);
			throw new NoSuchUserException();
		}
		logger.info("User Exists with userId: "+userId);
	}

}
