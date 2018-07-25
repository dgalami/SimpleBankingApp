package com.banking.UnitTest;

import java.io.IOException;
import java.util.Scanner;

import com.banking.database.Repository;
import com.banking.exceptions.InsufficientFundException;
import com.banking.models.Account;
import com.banking.models.Address;
import com.banking.models.Customer;
import com.banking.models.Person;

public class test {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
//		Address address1 = new Address("1234 Street st.", "kansas city","KS", "66333");
//		Address address2 = new Address("1234 Lindell blvd", "St Louis","MO", "63108");
//		try {
//			Customer customer1 = new Customer("Danial", "Dave", "danialdave@gmail.com", "51339993333", address1);
//			Customer customer2 = new Customer("Deepak", "Galami", "dipakgalami@gmail.com", "51339993333", address2);
//			customer1.customerToFile();
//			customer2.customerToFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Repository.readCustomerFile();
		Repository.displayCustomer();
		
		Customer customer = null;
		System.out.print("Enter account number to enter: ");
		Long data = input.nextLong();
		for(Long acNo : Repository.customers.keySet()) {
			if(data.equals(acNo)) {
				Person p = Repository.customers.get(acNo);
				Address address = new Address(p.getAddress().getStreet(),p.getAddress().getCity(),p.getAddress().getState(),p.getAddress().getZipCode());
				try {
					customer = new Customer(p.getFirstName(),p.getLastName(),p.getEmail(),p.getPhoneNo(),address);
					customer.setAccountNumber(acNo);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		}
		
		System.out.println(customer.toString());
		
		try {
			customer.createAccount(1000, "Checking Account", customer.getAccountNumber());
			customer.createAccount(2000, "Saving Account", customer.getAccountNumber());
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//Test LogFile and Transactions
		Repository.readAccountFile(customer.getAccountNumber());
		System.out.println();
		Repository.displayAccounts();
		
		Account[] account = new Account[Repository.accounts.size()];
		for(int i=0; i<Repository.accounts.size(); i++) {
			account[i] = Repository.accounts.get(i);
		}

		System.out.print("Which account you want to use: ");
		Long type = input.nextLong();
		for(int i =0; i<account.length; i++) {
			if(type.equals(account[i].getAccountNumber())) {
				
				
				try {
					System.out.println("Deposit $500");
					account[0].depositBalance(500.00);
					Repository.updateAccount(account[0], customer);
					System.out.println("Balance after update -> " + account[0].getBalance());
					
					System.out.println("Deposit $700");
					account[0].depositBalance(700.00);
					Repository.updateAccount(account[0], customer);
					System.out.println("Balance after update -> " + account[0].getBalance());
					
					System.out.println("Withdraw $200");
					account[0].withdrawProcess(200.00);
					Repository.updateAccount(account[0], customer);
					System.out.println("Balance after update -> " + account[0].getBalance());
					
					System.out.println("Transfer to another account-  $600");
					account[0].transferBalance(account[1], 600.00);
					Repository.updateAccount(account[0], customer);
					Repository.updateAccount(account[1], customer);
					System.out.println("Balance after update -> " + account[0].getBalance() + " -- " + account[1].getBalance());
					
					
				} catch (IOException | InsufficientFundException e) {
					
					e.printStackTrace();
				}
			break;
			}
		}
		


	}

}
