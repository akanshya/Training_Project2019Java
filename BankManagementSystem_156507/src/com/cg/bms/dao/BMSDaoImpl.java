package com.cg.bms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Customer;
import com.cg.bms.utility.JdbcUtility;

public class BMSDaoImpl implements BMSDao {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	/**
	 *  method name : createAccount
	 *  argument : CVustomer object
	 *  return type : long value
	 *  description : this 
	 *  Author : capgemini
	 *  creation date : 26-Jult-2019 
	 */
	
	@Override
	public long createAccount(Customer customer) throws BMSException {

		long id = 0;
		connection = JdbcUtility.getConnection();
		try {
			statement = connection.prepareStatement(QueryMapper.INSERT_CUSTOMER_QUERY);
			statement.setString(1, customer.getName());

			Date date = Date.valueOf(customer.getBirthDate());
			statement.setDate(2, date);

			statement.setString(3, customer.getPanNo());
			statement.setDouble(4, customer.getBalance());

			statement.executeUpdate();

			statement = connection.prepareStatement(QueryMapper.GET_GEN_ID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getLong(1);
			}

		} catch (SQLException e) {
			throw new BMSException("problem while creating statement");
		}
		return id;
	}

	@Override
	public Customer getCustomerDetails(long accNo) throws BMSException {

		connection = JdbcUtility.getConnection();
		Customer customer = null;
		try {
			statement = connection.prepareStatement(QueryMapper.GET_ACCOUNT_DETAILS);
			statement.setLong(1, accNo);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				long accountNo = resultSet.getLong(1);
				String name = resultSet.getString(2);
				Date date = resultSet.getDate(3);
				LocalDate localDate = date.toLocalDate();
				String pan = resultSet.getString(4);
				double balance = resultSet.getDouble(5);

				customer = new Customer(accountNo, name, localDate, pan, balance);
			}

		} catch (SQLException e) {
			throw new BMSException("problem while creating statement");
		}

		if (customer == null) {
			throw new BMSException("No customer presen twith the given account number");
		}

		return customer;
	}
}
