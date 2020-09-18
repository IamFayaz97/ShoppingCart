package com.shoppingcart.exception;

public class NoSuchProductException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3560638925103913413L;

	public NoSuchProductException(){
		super("Oops...Looks like, no such product");
	}
}
