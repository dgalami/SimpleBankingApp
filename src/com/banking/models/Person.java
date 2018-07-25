package com.banking.models;

public class Person {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	private Address address;
	
	public Person() {
	}

	public Person(String firstName, String lastName, String email, String phoneNo, Address address) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + email + "," + phoneNo + "," + address.toString();
	}
	
	
}
