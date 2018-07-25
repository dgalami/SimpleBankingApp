package com.banking.models;

import java.io.IOException;
import java.util.Date;

import com.banking.database.Repository;
import com.banking.exceptions.InsufficientFundException;

public abstract class Account {
	private double balance = 0.0;
	private String AccountType;
	private Long accountNumber;

	
	
	public Account() {
		
	}
	public Account(double balance, String type, Long acNumber) throws IOException {
		this.AccountType = type;
		this.setBalance(balance);
		this.accountNumber = acNumber;
	}
	
	//getter and setter
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	//methods
	public void depositBalance(double amount) throws IOException{
		this.balance += amount;
		toLogList("Deposit Amount", amount);
	}
	

	public void withdrawProcess(double amount) throws InsufficientFundException, IOException {
		if((getBalance() > 0) && (getBalance() - amount) > -1) {
			setBalance(getBalance() - amount);
			toLogList("Withdrawn Amount", amount);
		} else {
			throw new InsufficientFundException();
		}
	}
	

	public void transferBalance(Account a, double amount) throws InsufficientFundException, IOException {
		if((this.getBalance() > 0) && (this.getBalance() - amount) > -1) {
			this.setBalance(getBalance() - amount);
			a.depositBalance(amount);
			
			toLogList("Transfer Amount to " + a.getAccountType(), amount);
		} else {
			throw new InsufficientFundException();
		}
	}
	
	public void toLogList(String disc, double amount) throws IOException {
		Date date = new Date();
		String ct = String.format("%1$tY-%1$tm-%1$td", date);
		String result = ct + "," + disc + "," + amount;
		
		Repository.writeLogFile(getAccountNumber(), result);
	}


}
