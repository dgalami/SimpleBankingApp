package com.banking.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.banking.models.Account;
import com.banking.models.Address;
import com.banking.models.CheckingAccount;
import com.banking.models.Customer;
import com.banking.models.Person;
import com.banking.models.SavingAccount;

public class Repository {
	
	//store data from file. Key -- account number and value -- Customer.toString()
		public static HashMap<Long, Person> customers = new HashMap<Long, Person>();	//retrive data from
		
		//Store Accounts of per customer
		public static ArrayList<Account> accounts = new ArrayList<Account>();
		
		//store transactions
		public static ArrayList<String> logLists = new ArrayList<String>();	
		
		public static final String path = "/Users/deepakgalami/eclipse-workspace/SimpleBankingProject/";
		
	public String custfileName;
	Scanner s;
	
	/***load file and store it in array. **/
	public static void readCustomerFile() {
		String fileName = path + "customerInfo.txt";
		File file = new File(fileName);
		 try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 String str;
			 while ((str = br.readLine()) != null) {
				String[] tokens = str.trim().split(",");
				customers.put(Long.parseLong(tokens[0]), new Person(tokens[1],tokens[2],tokens[3],tokens[4], new Address(tokens[5],tokens[6],tokens[7],tokens[8])));
			 }
			 System.out.println("Customer File Load successfully");
			 br.close();
		  } catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
	}
	

	
	public static void readAccountFile(Long accountNumber) {
		String fileName = path + accountNumber +".txt";
		File file = new File(fileName);
		Account ac = null;
		 try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 String st;
			 while ((st = br.readLine()) != null) {
				String[] tokens = st.split(",");
				if(tokens[1].equalsIgnoreCase("Checking Account")) {
					ac = new CheckingAccount(Double.parseDouble(tokens[2]));
					ac.setAccountNumber(Long.parseLong(tokens[0]));
					accounts.add(ac);
				} else if(tokens[1].equalsIgnoreCase("Saving Account")) {
					ac = new SavingAccount(Double.parseDouble(tokens[2]));
					ac.setAccountNumber(Long.parseLong(tokens[0]));
					accounts.add(ac);
				}
			 }
			 br.close();
		  } catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
	}
	
	
	public static void writeAccountFile(Long custAccountNumber, Account ac){
		String fileName = path + custAccountNumber +".txt";
		File file = new File(fileName);
		try {
			//create the file
			if(file.createNewFile()) {
				FileWriter writer = new FileWriter(file);
				//write to file
				writer.write(ac.toString());
				writer.close();
				System.out.println("File successfully created and written");
			} else {
				//append to existing file
				FileWriter fw = new FileWriter(file,true); 
				fw.write(ac.toString());
				fw.close();
				System.out.println("Account File successfully appended");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void updateAccount(Account ac, Customer c) {
		String fileName = path + c.getAccountNumber() +".txt";
		ArrayList<String> tempArray = new ArrayList<String>();
		File file = new File(fileName);
		try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 String st;
			 while ((st = br.readLine()) != null) {
				String[] tokens = st.split(",");
				if(!(tokens[0].equalsIgnoreCase(String.valueOf(ac.getAccountNumber())))) {
					tempArray.add(st);
				}
			 }
			 br.close();
			 
			 FileWriter fw = new FileWriter(file,false);
			 for(String str : tempArray) {
				 fw.write(str + "\n");
			 }
			 fw.write(ac.toString());
			 fw.close();
		  } catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		
		
	}
	
	public static void writeLogFile(Long accountNumber, String report){
		String fileName = path + "log_"+ accountNumber +"_.txt";
		File file = new File(fileName);
		
		try {
			//create the file
			if(file.createNewFile()) {
				FileWriter write = new FileWriter(file);
				write.write(report+"\n");
				write.close();
				System.out.println("File successfully created and written");
			} else {
				//append to existing file
				FileWriter fw = new FileWriter(file,true); 
				fw.write(report+"\n");
				fw.close();
				System.out.println("Log File successfully appended");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readLogFile(Long custAccountNumber) {
		String fileName = path + custAccountNumber + ".txt";
		File file = new File(fileName);
		 try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 String st;
			 while ((st = br.readLine()) != null) {
				logLists.add(st);
			 }
			 br.close();
		  } catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void displayLog() {
		if(logLists.size() > 0) {
			for(String str : logLists) {
				System.out.println(str);
			}
		} else {
			System.out.println("No transcations");
		}
		
	}
	
	public static void displayAccounts() {
		if(accounts.size() > 0) {
			for(Account a : accounts) {
				System.out.println(a.toString());
			}
		} else {
			System.out.println("No transcations");
		}
		
	}
	
	public static void displayCustomer() {
		if(customers.size() > 0) {
			for(Long key : customers.keySet()) {
				System.out.println(key + "-->" + customers.get(key).toString());
			}
		} else {
			System.out.println("No transcations");
		}
	}

}
