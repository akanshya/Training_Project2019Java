package com.cg.bms.model;

import java.time.LocalDate;

public class Customer {

	private long AccountNumber;
	private String name;
	private LocalDate birthDate;
	private String panNo;
	private double balance;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, LocalDate birthDate, String panNo, double balance) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.panNo = panNo;
		this.balance = balance;
	}

	public Customer(long accountNumber, String name, LocalDate birthDate, String panNo, double balance) {
		super();
		AccountNumber = accountNumber;
		this.name = name;
		this.birthDate = birthDate;
		this.panNo = panNo;
		this.balance = balance;
	}

	public long getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer [AccountNumber=" + AccountNumber + ", name=" + name + ", birthDate=" + birthDate + ", panNo="
				+ panNo + ", balance=" + balance + "]";
	}

}
