package com.shoppingcart.exception;

public class ProductNotPresentInCartException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8496003345173342632L;

	public ProductNotPresentInCartException() {
		super("Please add the product in cart first");
	}
}
