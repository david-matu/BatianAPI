package com.dave.apis.BatianAPI.service;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dave.apis.BatianAPI.core.Customer;

import com.dave.apis.BatianAPI.dao.AppDAO;
import com.dave.apis.BatianAPI.dao.CustomerDAO;

import com.dave.apis.BatianAPI.util.AppConstants;
public abstract class CustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	@CreateSqlObject
	abstract AppDAO appDAO();

	@CreateSqlObject
	abstract CustomerDAO customerDAO();

	public Customer createCustomer(Customer customer) {
		LOGGER.info("Creating Customer ::::: \n" + customer.toString());

		Customer c = customer;

		c.setUserId(appDAO().getUniqueIntegerItemID());
		customerDAO().createCustomer(c);
		return customerDAO().getCustomer(c.getUserId());
	}

	//Read a Single Customer
	public Customer getCustomer(long userId) {
		LOGGER.info("Retrieving Customer ::::: supplied ID: " + userId);

		Customer c = customerDAO().getCustomer(userId);

		if(Objects.isNull(c)) {
			throw new WebApplicationException(String.format(AppConstants.ENTITY_NOT_FOUND.toString(), userId), Status.NOT_FOUND);
		}

		return c;
	}

	public List<Customer> getCustomers() {
		LOGGER.info("Retrieving all Customers");

		return customerDAO().getCustomers();
	}

	//Update Customer
	public Customer editCustomer(Customer customer ) {
		if(Objects.isNull(customerDAO().getCustomer(customer.getUserId()))) {
			throw new WebApplicationException(String.format(AppConstants.ENTITY_NOT_FOUND.toString(), Status.NOT_FOUND));
		}
		customerDAO().updateCustomer(customer);
		return customerDAO().getCustomer(customer.getUserId());
	}

	//DELETE Customer
	public String deleteCustomer(final long userId) {
		int result = customerDAO().deleteCustomer(userId);
		LOGGER.info("Results after attempting delete via CustomerService.deleteCustomer(): ", result);

		switch(result) {
		case 1:
			return AppConstants.SUCCESS.toString();
		case 0:
			throw new WebApplicationException(String.format(AppConstants.ENTITY_NOT_FOUND.toString(), Status.NOT_FOUND));
    		
			default:
				throw new WebApplicationException(String.format(AppConstants.UNEXPECTED_DELETE_ERROR.toString(), Status.INTERNAL_SERVER_ERROR));
		}
	}

	public String performHealthCheck() {
    	try {
    		customerDAO().getCustomers();
    	} catch(UnableToObtainConnectionException ex) {
    		return AppService.checkUnableToObtainConnectionException(ex);
    	} catch(UnableToExecuteStatementException ex) {
    		return AppService.checkUnableToExecuteStatementException(ex);
    	}
    	return null;
    }

}
