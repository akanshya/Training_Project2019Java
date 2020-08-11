package com.cg.bms.dao;

import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Customer;

public interface BMSDao {

	long createAccount(Customer customer) throws BMSException;

	Customer getCustomerDetails(long accNo) throws BMSException;

}
