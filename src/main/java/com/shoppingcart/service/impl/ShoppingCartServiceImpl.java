package com.shoppingcart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.dao.CartDao;
import com.shoppingcart.dao.ProductDao;
import com.shoppingcart.dao.UserDao;
import com.shoppingcart.entity.Cart;
import com.shoppingcart.entity.Item;
import com.shoppingcart.entity.Product;
import com.shoppingcart.entity.User;
import com.shoppingcart.exception.EmptyListOfProductsException;
import com.shoppingcart.exception.InternalErrorException;
import com.shoppingcart.exception.InvalidQuantityException;
import com.shoppingcart.exception.NoSuchProductException;
import com.shoppingcart.exception.NoSuchUserException;
import com.shoppingcart.exception.ProductNotPresentInCartException;
import com.shoppingcart.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	ProductDao productDao;

	@Override
	public List<User> getAllUsers() {
		// TODO Getting all Users
		logger.info("Fetching all Users");
		return userDao.getAllUsers();
	}

	@Override
	public User getUser(int userId) throws NoSuchUserException {
		// TODO Getting a single User
		logger.info("Fetching a user with userId: "+userId);
		return userDao.getUser(userId);
	}

	@Transactional(rollbackFor = InternalErrorException.class)
	@Override
	public User addUser(String userName) {
		// TODO Adding a new User
		logger.info("Creating a new User with Name : "+userName);
		return userDao.addUser(userName);
	}

	@Transactional(rollbackFor = InternalErrorException.class)
	@Override
	public User updateUser(int userId, String userName) throws NoSuchUserException {
		// TODO Updating an existing User
		logger.info("Performing update of name to "+userName+" for User with Id : "+userId);
		return userDao.updateUser(userId, userName);
	}

	@Transactional(rollbackFor = InternalErrorException.class)
	@Override
	public void deleteUser(int userId) throws NoSuchUserException {
		// TODO Delete an existing User
		logger.info("Performing deletion of user with Id: "+userId);
		userDao.deleteUser(userId);
	}

	@Override
	public void checkUser(int userId) throws NoSuchUserException {
		// TODO Check whether User exists with provided UserId
		logger.info("Checking if user exists with userId: "+userId);
		userDao.checkUser(userId);
	}

	@Override
	public List<Item> getProductsInCart(int cartId) throws InternalErrorException {
		// TODO Get List of Products and their Quantity from the cart with provided cartId
		logger.info("Getting products from Cart of User with Id : "+cartId);
		return cartDao.getProductsInCart(cartId);
	}

	@Transactional(rollbackFor = InternalErrorException.class)
	@Override
	public Cart addProductInCart(int cartId, int productId) {
		// TODO Add a new Product in Cart with cartId and productId
		logger.info("Performing addition of product with productId : "+productId+" into Cart of user with userId :"+cartId);
		return cartDao.addProductInCart(cartId, productId);
	}

	@Transactional(rollbackFor = InternalErrorException.class)
	@Override
	public Cart updateProductQuantityInCart(int cartId, int productId, int quantity)
			throws ProductNotPresentInCartException, InvalidQuantityException {
		// TODO Update a Product's Quantity in cart with CartId, productId and quantity
		logger.info("Performing Update on quantity for productId "+productId+" in cart of User "+cartId);
		return cartDao.updateProductQuantityInCart(cartId, productId, quantity);
	}

	@Transactional(rollbackFor = InternalErrorException.class)
	@Override
	public Cart removeProductFromCart(int cartId, int productId) throws ProductNotPresentInCartException {
		// TODO Remove a Product from Cart
		logger.debug("Performing deletion of product with productId : "+productId+" from cart");
		return cartDao.removeProductFromCart(cartId, productId);
	}

	@Transactional(rollbackFor = InternalErrorException.class)
	@Override
	public Cart removeAllProductsFromCart(int cartId) {
		// TODO Remove all Products from Cart, making the cart empty of products
		logger.debug("Performing reset of cart by deleting all products");
		return cartDao.removeAllProductsFromCart(cartId);
	}

	@Override
	public Cart getCart(int cartId) {
		// TODO Get Cart Content
		logger.debug("Fetching cart of user with userId : "+cartId);
		return cartDao.getCart(cartId);
	}

	@Override
	public List<Product> getAllProducts() throws InternalErrorException {
		// TODO List all the Products
		logger.debug("Fetching all products");
		return productDao.getAllProducts();
	}

	@Override
	public Product searchProductByProductId(int productId) throws NoSuchProductException {
		// TODO Search a product based on its Id
		logger.debug("Searching for a product based on its productId : "+productId);
		return productDao.searchProductByProductId(productId);
	}

	@Override
	public Product searchProductByProductName(String prodName) throws NoSuchProductException {
		// TODO Search a Product based on its name
		logger.debug("Searching for a product based on its name : "+prodName);
		return productDao.searchProductByProductName(prodName);
	}

	@Override
	public List<Product> searchProductsByCategory(String category) throws EmptyListOfProductsException {
		// TODO Search Products based on a category
		logger.debug("Searching list of products based on category : "+category);
		return productDao.searchProductsByCategory(category);
	}

	@Override
	public void checkProduct(int productId) throws NoSuchProductException {
		// TODO Check a product's existence in the Product's repository
		logger.debug("Verifying whether any products exists with productId : "+productId );
		productDao.checkProduct(productId);
	}

}
