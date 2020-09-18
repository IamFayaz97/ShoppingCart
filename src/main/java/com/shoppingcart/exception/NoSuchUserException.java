package com.shoppingcart.exception;

public class NoSuchUserException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2973954072855164973L;

	public NoSuchUserException() {
		super("Oops...No such user");
	}
}
