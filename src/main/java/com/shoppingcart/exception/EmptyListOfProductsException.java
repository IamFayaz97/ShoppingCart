package com.shoppingcart.exception;

public class EmptyListOfProductsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -42520092741962318L;

	public EmptyListOfProductsException() {
		super("Looks like no products with searched Category");
	}
}
