package com.cg.bms.dao;

public interface QueryMapper {

	public String INSERT_CUSTOMER_QUERY = "INSERT INTO accounts_master(account_number,cust_name,dob,pan,balance) values(accounts_sequence.nextval,?,?,?,?)";

	public String GET_GEN_ID = "SELECT accounts_sequence.currval from dual";

	public String GET_ACCOUNT_DETAILS = "SELECT *from accounts_master WHERE account_number=?";
}
