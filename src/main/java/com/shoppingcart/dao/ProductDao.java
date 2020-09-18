package com.shoppingcart.dao;

import java.util.List;

import com.shoppingcart.entity.Product;
import com.shoppingcart.exception.EmptyListOfProductsException;
import com.shoppingcart.exception.InternalErrorException;
import com.shoppingcart.exception.NoSuchProductException;

public interface ProductDao {
	
	List<Product> getAllProducts() throws InternalErrorException ;
	Product searchProductByProductId(int productId) throws NoSuchProductException;
	Product searchProductByProductName(String prodName) throws NoSuchProductException;
	List<Product> searchProductsByCategory(String category) throws EmptyListOfProductsException;
	void checkProduct(int productId) throws NoSuchProductException;

}
