package com.shoppingcart.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoppingcart.dao.CartDao;
import com.shoppingcart.entity.Cart;
import com.shoppingcart.entity.Item;
import com.shoppingcart.exception.InvalidQuantityException;
import com.shoppingcart.exception.ProductNotPresentInCartException;
import com.shoppingcart.repository.CartRepository;
import com.shoppingcart.repository.ProductRepository;

@Component
public class CartDaoImpl implements CartDao{
	
	Logger logger = LoggerFactory.getLogger(CartDaoImpl.class);

	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Cart getCart(int cartId){
		// get single cart
		Cart cart = cartRepository.findById(cartId).get();
		logger.info("cart found as follows: "+cart.toString());
		return cart;
	}

	@Override
	public List<Item> getProductsInCart(int cartId) {
		// TODO get products in cart
		Map<Integer,Integer> productQuantityMap = cartRepository.findById(cartId).get().getProductQuantityMap();
		logger.debug("Since we are taking productId and quantity in cart, creating a map of product from productId and quantity.");
		List<Item> itemList = new ArrayList<Item>();
		for (Map.Entry<Integer,Integer> entry : productQuantityMap.entrySet()) {
			Item item = new Item(productRepository.findById(entry.getKey()).get(),entry.getValue());
			itemList.add(item);
		}
		logger.debug("List of products with quantity is provided for user with userId: "+cartId);
		return itemList;
	}

	@Override
	public Cart addProductInCart(int cartId, int productId){
		// TODO add a product in cart
			Cart cart = cartRepository.findById(cartId).get();
			try {  //check if product is present in cart
				this.checkProductInCart(cart, productId);
				int initialQuantity = cart.getProductQuantityMap().get(productId);
				logger.debug("Product is already present in cart, so incrementing quantity");
				cart.getProductQuantityMap().replace(productId, initialQuantity+1);
			} catch (ProductNotPresentInCartException e) {
				logger.debug("Since, product is not present in cart, so adding in cart with quantity=1");
				cart.getProductQuantityMap().put(productId,1);
			}
			cart.setCartPrice(this.calculateCartPrice(cart,productId,1));
			return cartRepository.save(cart);
	}

	@Override
	public Cart updateProductQuantityInCart(int cartId, int productId, int newQuantity)
			throws ProductNotPresentInCartException, InvalidQuantityException {
		// TODO update product quantity in cart
			Cart cart = cartRepository.findById(cartId).get();
			if(this.checkProductInCart(cart, productId)) { //check if product is present in cart
				int modifiedQuantity = newQuantity-cart.getProductQuantityMap().get(productId);
				if(newQuantity == 0) {
					logger.debug("Since user added Quantity as 0, so removing the product");
					cart.setCartPrice(this.calculateCartPrice(cart, productId, newQuantity));
					cart.getProductQuantityMap().remove(productId);
				}
				else if(newQuantity<0) {
					logger.warn("Quantity can never be negative");
					throw new InvalidQuantityException();
				}
				else if(newQuantity!=0 && modifiedQuantity!=0){
					logger.debug("Updating the quantity and Cart Price");
					cart.getProductQuantityMap().replace(productId, newQuantity);
					cart.setCartPrice(this.calculateCartPrice(cart, productId, modifiedQuantity));
					}
				return cartRepository.save(cart);
				}
				else {
					logger.warn("Cannot update a product which is not in cart");
					throw new ProductNotPresentInCartException();
				}
	}

	@Override
	public Cart removeProductFromCart(int cartId, int productId) throws ProductNotPresentInCartException{
		// TODO delete a product from cart
			Cart cart = cartRepository.findById(cartId).get();
			this.checkProductInCart(cart, productId); //check if product is present in cart
			int quantity = cart.getProductQuantityMap().get(productId);
			cart.setCartPrice(this.calculateCartPrice(cart, productId, -quantity));
			cart.getProductQuantityMap().remove(productId);
			logger.info("Removing the product with Id : "+productId+" from cart");
			return cartRepository.save(cart);
	}	
	@Override
	public Cart removeAllProductsFromCart(int cartId){
		// TODO delete all products from cart
			Cart cart = cartRepository.findById(cartId).get();
			cart.getProductQuantityMap().clear();
			cart.setCartPrice(0);
			logger.info("Clearing the contents of Cart");
			return cartRepository.save(cart);
	}
	
	
	//method to calculate the cart Price
	public float calculateCartPrice(Cart cart, int productId, int modifiedQuantity) {
		float productPrice = productRepository.findById(productId).get().getPrice();
		logger.debug("calculating Cart price");
		if(modifiedQuantity==0) {
			int quantity = cart.getProductQuantityMap().get(productId);
			logger.debug("Rationalizing the cartPrice to remove the nullified product price");
			return cart.getCartPrice()-(productPrice*quantity);
		}
		else {
			float updatedPrice = cart.getCartPrice()+(productPrice*modifiedQuantity);
			logger.debug("Updated Cart Price as Rs."+updatedPrice+" which was previously Rs."+cart.getCartPrice());
			return updatedPrice;
		}
	}
	
	public boolean checkProductInCart(Cart cart, int productId) throws ProductNotPresentInCartException {
		logger.debug("Verifying whether the product is present in cart");
		if(cart.getProductQuantityMap().containsKey(productId)) {
			logger.debug("Product is present in cart");
			return true;
		}
		else {
			logger.error("No Product in cart with productId : "+productId);
			throw new ProductNotPresentInCartException();
		}
	}

}
