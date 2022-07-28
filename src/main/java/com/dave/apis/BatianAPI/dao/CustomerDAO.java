package com.dave.apis.BatianAPI.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.dave.apis.BatianAPI.core.Customer;
import com.dave.apis.BatianAPI.mappers.CustomerMapper;

@RegisterMapper(CustomerMapper.class)
public interface CustomerDAO {

	@SqlQuery("SELECT * FROM CUSTOMER")
	abstract List<Customer> getCustomers();

	@SqlQuery("SELECT * FROM CUSTOMER WHERE USER_ID = :userId")
	public Customer getCustomer(@Bind("userId") final long userId);

	@SqlUpdate("INSERT INTO CUSTOMER(USER_ID, FIRST_NAME, LAST_NAME, PHONE, EMAIL, PASSWORD, REG_DATE ) values(:userId, :firstName, :lastName, :phone, :email, :password, :regDate )")
	void createCustomer(@BindBean final Customer customer);

	@SqlUpdate("UPDATE CUSTOMER SET USER_ID = coalesce(:userId, USER_ID), FIRST_NAME = coalesce(:firstName, FIRST_NAME), LAST_NAME = coalesce(:lastName, LAST_NAME), PHONE = coalesce(:phone, PHONE), EMAIL = coalesce(:email, EMAIL), PASSWORD = coalesce(:password, PASSWORD), REG_DATE = coalesce(:regDate, REG_DATE) WHERE USER_ID = :userId ")
	void updateCustomer(@BindBean final Customer customer);

	@SqlUpdate("DELETE FROM CUSTOMER WHERE USER_ID = :userId")
	int deleteCustomer(@Bind("userId") final long userId);

	}
