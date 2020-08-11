package com.cg.bms.service;

import java.util.regex.Pattern;

import com.cg.bms.dao.BMSDao;
import com.cg.bms.dao.BMSDaoImpl;
import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Customer;

public class BMSServiceImpl implements BMSService {

	BMSDao bmsDao = new BMSDaoImpl();

	@Override
	public boolean validateName(String name) throws BMSException {

		boolean nameFlag = false;
		String nameRegEx = "[A-Z]{1}[a-zA-Z\\s]{4,19}";

		if (Pattern.matches(nameRegEx, name)) {
			nameFlag = true;
		} else {
			throw new BMSException("length should br 5 to 20 & should start with uppercase");
		}
		return nameFlag;
	}

	@Override
	public boolean validateDOB(String date) throws BMSException {

		boolean result = false;

		String dateRegEx = "\\d{4}-\\d{2}-\\d{2}";
		if (Pattern.matches(dateRegEx, date)) {
			result = true;
		} else {
			throw new BMSException("Date should be in yyyy-MM-dd format");
		}
		return result;

	}

	@Override
	public boolean validatePan(String panNo) throws BMSException {

		boolean panFlag = false;
		String panRegEx = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

		if (Pattern.matches(panRegEx, panNo)) {
			panFlag = true;
		} else {
			throw new BMSException("PAN number is not in the valid format");
		}
		return panFlag;
	}

	@Override
	public boolean validateBalance(double balance) throws BMSException {

		boolean flag = false;
		if (balance < 10000) {
			throw new BMSException("min balance should be >=10000");
		} else {
			flag = true;
		}
		return flag;
	}

	@Override
	public long createAccount(Customer customer) throws BMSException {
		return bmsDao.createAccount(customer);

	}

	@Override
	public boolean validateAccNo(long accNo) throws BMSException {

		boolean flag = false;

		if (Pattern.matches("\\d{4}", String.valueOf(accNo))) {
			flag = true;
		} else {
			throw new BMSException("acc number should contain only 4 digits");
		}
		return flag;

	}

	@Override
	public Customer getAccountDetails(long accNo) throws BMSException {
		return bmsDao.getCustomerDetails(accNo);
	}
}
