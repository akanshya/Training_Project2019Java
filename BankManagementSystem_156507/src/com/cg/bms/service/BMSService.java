package com.cg.bms.service;

import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Customer;

public interface BMSService {

	boolean validateName(String name) throws BMSException;

	boolean validateDOB(String date) throws BMSException;

	boolean validatePan(String panNo) throws BMSException;

	boolean validateBalance(double balance) throws BMSException;

	long createAccount(Customer customer) throws BMSException;

	boolean validateAccNo(long accNo) throws BMSException;

	Customer getAccountDetails(long accNo) throws BMSException;

}
