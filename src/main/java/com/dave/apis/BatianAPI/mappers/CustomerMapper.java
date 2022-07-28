package com.dave.apis.BatianAPI.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.dave.apis.BatianAPI.core.Customer;

public class CustomerMapper implements ResultSetMapper<Customer> {

	@Override
	public Customer map(int index, ResultSet rs, StatementContext ctx) throws SQLException {

		Customer c = new Customer();
		c.setUserId(rs.getLong("USER_ID"));
		c.setFirstName(rs.getString("FIRST_NAME"));
		c.setLastName(rs.getString("LAST_NAME"));
		c.setPhone(rs.getString("PHONE"));
		c.setEmail(rs.getString("EMAIL"));
		c.setPassword(rs.getString("PASSWORD"));
		c.setRegDate(rs.getTimestamp("REG_DATE"));
		return c;
	}
}
