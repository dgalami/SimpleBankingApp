package com.banking.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.banking.database.Repository;
import com.banking.exceptions.InsufficientFundException;

public class Customer extends Person{
	private Long accountNumber;	
	
	public Customer() {
	}
	
	public Customer(String firstName, String lastName, String email, String phoneNo, Address address) throws IOException{
		super(firstName, lastName, email, phoneNo, address);
		this.accountNumber = accountNumberGenerator();
	}
	
	//getters and setters

	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long ac) {
		this.accountNumber = ac;
	}

	//create new file for first customer
	public void customerToFile() throws IOException {
		File file = new File(Repository.path + "customerInfo.txt");
		//create the new file 
		if(file.createNewFile()) {
			//write to file
			FileWriter write = new FileWriter(file);
			write.write(this.toString());	//write to file
			write.close();
			System.out.println("File successfully created");
		} else {
			//file already exist -- overwrite
			FileWriter write = new FileWriter(file,true); //append
			write.write(this.toString());	//write to file
			write.close();
			System.out.println("Customer appended Successfully");
			return;
		}
		
	}
	
	//create new account and store in Repository
	public void createAccount(double balance, String acType, Long custAcNo) throws IOException {
		Account ac = null;
		if(acType.equalsIgnoreCase("Checking Account")) {
			ac =  new CheckingAccount(balance);
		} else if (acType.equalsIgnoreCase("Saving Account")) {
			ac = new SavingAccount(balance);
		}
		
		Repository.writeAccountFile(custAcNo, ac);
	}
	
	/* Close account **/
	private void closeAccount(Account a) {
		Repository.accounts.remove(a);
		//overrite file with new repository
		//future : check balance > 0 ? withdraw : transfer to another account
	}
	
	private Long accountNumberGenerator() {
		//Min + (int)(Math.random() * ((Max - Min) + 1))
		return (long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L;
		//future: check repeating randon number 
	}
	
	
	public String toString() {
	String result = this.getAccountNumber() + "," + super.toString() + "\n";
		return result;
	}
	
}
