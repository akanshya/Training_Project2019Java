package com.cg.bms.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Customer;
import com.cg.bms.service.BMSService;
import com.cg.bms.service.BMSServiceImpl;

public class UIClass {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		BMSService service = new BMSServiceImpl();

		String choice = "";

		do {

			System.out.println("***** welcome to Bank Application ******");
			System.out.println("1.Create Account");
			System.out.println("2.Delete Account");
			System.out.println("3.update Account");
			System.out.println("4.Get Account detials by ID");
			System.out.println("5.get All Accounts ");
			System.out.println("6.Exit");

			int input = 0;
			boolean inputFlag = false;

			do {
				scanner = new Scanner(System.in);
				System.out.println("Enter ur choice(1-6)");

				try {
					input = scanner.nextInt();
					inputFlag = true;

					String name = "";
					boolean nameFlag = false;

					switch (input) {

					case 1:
						scanner.nextLine();
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter Name:");
							name = scanner.nextLine();
							try {
								service.validateName(name);
								nameFlag = true;
							} catch (BMSException e) {
								nameFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!nameFlag);

						String date = "";
						boolean dateFlag = false;
						LocalDate dob = null;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter DOB:(yyyy-MM-dd)");
							date = scanner.next();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							try {
								dob = LocalDate.parse(date, formatter);
								dateFlag = true;
							} catch (DateTimeParseException e) {
								dateFlag = false;
								System.err.println("date should be in the format of yyyy-MM-dd");
							}
						} while (!dateFlag);

						String panNo = "";
						boolean panFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter PAN number:");
							panNo = scanner.next();
							try {
								service.validatePan(panNo);
								panFlag = true;
							} catch (BMSException e) {
								panFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!panFlag);

						double balance = 0;
						boolean balanceFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter balance");
							try {
								balance = scanner.nextDouble();
								try {
									service.validateBalance(balance);
									balanceFlag = true;
								} catch (BMSException e) {
									System.err.println(e.getMessage());
								}
							} catch (InputMismatchException e) {
								balanceFlag = false;
								System.err.println("input should contains only digits");
							}
						} while (!balanceFlag);

						Customer customer = new Customer(name, dob, panNo, balance);
						long AccNo;
						try {
							AccNo = service.createAccount(customer);
							System.out.println("account created with acc no : " + AccNo);
						} catch (BMSException e) {
							System.err.println(e.getMessage());
						}

						break;

					case 2:

						break;
					case 3:

						break;

					case 4:

						long accNo = 0;
						boolean accFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter Acc number");
							try {
								accNo = scanner.nextLong();
								try {
									service.validateAccNo(accNo);
									accFlag = true;

									Customer customer2 = service.getAccountDetails(accNo);
									System.out.println(customer2);

								} catch (BMSException e) {
									System.err.println(e.getMessage());
								}
							} catch (InputMismatchException e) {
								accFlag = false;
								System.err.println("input should contains only digits");
							}
						} while (!accFlag);

						break;
					case 5:

						break;

					case 6:

						break;

					default:
						inputFlag = false;
						System.out.println("input should be in the range of 1-6");
						break;
					}

				} catch (InputMismatchException e) {
					inputFlag = false;
					System.err.println("input should contain only digits");
				}
			} while (!inputFlag);

			System.out.println("do u want to cintinue?(yes/no)");
			choice = scanner.next();

		} while (choice.equalsIgnoreCase("yes"));

	}

}
