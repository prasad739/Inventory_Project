package com.prasad.product.Exceptions;

@SuppressWarnings("serial")
public class SizeNotFoundException extends RuntimeException {

	public SizeNotFoundException() {

	}

	public SizeNotFoundException(String msg) {
		super(msg);
	}

}
