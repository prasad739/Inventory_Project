package com.prasad.product.Exceptions;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException() {
		
	}
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
	

}
