package com.shoppingcart.service;

import java.util.List;

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

public interface ShoppingCartService {

	/*
	 * 
	 * User related
	 *  
	 */
	List<User> getAllUsers();
	User getUser(int userId) throws NoSuchUserException;
	User addUser(String userName);
	User updateUser(int userId, String userName) throws NoSuchUserException;
	void deleteUser(int userid) throws NoSuchUserException;
	void checkUser(int userId) throws NoSuchUserException;
	
	/*
	 * 
	 * Cart related 
	 * 
	 */
	List<Item> getProductsInCart(int cartId) throws InternalErrorException;
	Cart addProductInCart(int cartId, int productId);
	Cart updateProductQuantityInCart(int cartId, int productId,int quantity) throws ProductNotPresentInCartException, InvalidQuantityException;
	Cart removeProductFromCart(int cartId, int productId) throws ProductNotPresentInCartException; 
	Cart removeAllProductsFromCart(int cartId);
	Cart getCart(int cartId);
	
	/*
	 * 
	 * Product related
	 *  
	 */
	List<Product> getAllProducts() throws InternalErrorException ;
	Product searchProductByProductId(int productId) throws NoSuchProductException;
	Product searchProductByProductName(String prodName) throws NoSuchProductException;
	List<Product> searchProductsByCategory(String category) throws EmptyListOfProductsException;
	void checkProduct(int productId) throws NoSuchProductException;
}
