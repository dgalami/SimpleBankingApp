package com.banking.exceptions;

public class InsufficientFundException extends Exception{

	private static final long serialVersionUID = 1L;

	public InsufficientFundException() {
		super("!Warning Balance is low.");
	}
}
