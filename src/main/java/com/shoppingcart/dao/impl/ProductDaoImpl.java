package com.shoppingcart.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppingcart.dao.ProductDao;
import com.shoppingcart.entity.Product;
import com.shoppingcart.exception.EmptyListOfProductsException;
import com.shoppingcart.exception.InternalErrorException;
import com.shoppingcart.exception.NoSuchProductException;
import com.shoppingcart.repository.ProductRepository;

@Component
public class ProductDaoImpl implements ProductDao{
	
    Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() throws InternalErrorException {
		// TODO get All products
		List<Product> productList = productRepository.findAll();
		if (productList == null) {
			logger.warn("Some Internal issue while fetching.");
			throw new InternalErrorException();
		}
		else
			return productList;
	}
	
	@Override
	public Product searchProductByProductId(int productId) throws NoSuchProductException {
		// TODO search a product in product repository
		if(productRepository.findById(productId).isPresent()) {
			Product product = productRepository.findById(productId).get();
			logger.info("Found product as "+product.toString());
			return product;
		}
		else {
			logger.warn(" No such product in list with product Id : "+productId);
			throw new NoSuchProductException();
		}
	}

	@Override
	public Product searchProductByProductName(String prodName) throws NoSuchProductException {
		// TODO search a product by Name in product repository
		Product product = productRepository.findByProdName(prodName);
		if(product == null) {
			logger.warn(" No such product in list with name : "+prodName);
			throw new NoSuchProductException();
		}
		logger.info("Found product is "+product.toString());
		return product;
	}

	@Override
	public List<Product> searchProductsByCategory(String category) throws EmptyListOfProductsException {
		// TODO search products based on category
		if(productRepository.findByCategory(category).isEmpty()) {
			logger.warn(" No products of category : "+category);
			throw new EmptyListOfProductsException();
		}
		logger.info(" Products are found for the category :"+category);
		return productRepository.findByCategory(category.toLowerCase());
	}

	@Override
	public void checkProduct(int productId) throws NoSuchProductException {
		// TODO check whether the product exists in cart
		if(productRepository.findById(productId).isPresent()) {
			logger.info("Product exists with Id : "+productId);
		}
		else {
			logger.warn("No product with Id : "+productId);
			throw new NoSuchProductException();
		}
	}


}
