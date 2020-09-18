package com.shoppingcart.dao;

import java.util.List;

import com.shoppingcart.entity.Cart;
import com.shoppingcart.entity.Item;
import com.shoppingcart.exception.InternalErrorException;
import com.shoppingcart.exception.InvalidQuantityException;
import com.shoppingcart.exception.ProductNotPresentInCartException;

public interface CartDao {
	
	//products in cart functionality
	List<Item> getProductsInCart(int cartId) throws InternalErrorException;
	Cart addProductInCart(int cartId, int productId);
	Cart updateProductQuantityInCart(int cartId, int productId,int quantity) throws ProductNotPresentInCartException, InvalidQuantityException;
	Cart removeProductFromCart(int cartId, int productId) throws ProductNotPresentInCartException; 
	Cart removeAllProductsFromCart(int cartId);
	
	//cart functionality
	Cart getCart(int cartId);

}
