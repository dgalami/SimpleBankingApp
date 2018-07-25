package com.banking.models;

import java.io.IOException;

public class SavingAccount extends Account{
	
	public SavingAccount() {}
	
	public SavingAccount(double balance) throws IOException {
		
		super(balance, "Saving Account", setTypeAccountNumber());
		 
	}
	
	
	private static Long setTypeAccountNumber() {
		return (long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L;
	}
	
	@Override
	public String toString() {
		String result = this.getAccountNumber() + "," + this.getAccountType() + "," + this.getBalance() + "\n";
		return result;
	}
	
	


}
