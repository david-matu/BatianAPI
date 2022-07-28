package com.dave.apis.BatianAPI.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.dave.apis.BatianAPI.core.Staff;

public class StaffMapper implements ResultSetMapper<Staff> {

	@Override
	public Staff map(int index, ResultSet rs, StatementContext ctx) throws SQLException {

		Staff s = new Staff();
		s.setUserId(rs.getLong("USER_ID"));
		s.setFirstName(rs.getString("FIRST_NAME"));
		s.setLastName(rs.getString("LAST_NAME"));
		s.setPhone(rs.getString("PHONE"));
		s.setEmail(rs.getString("EMAIL"));
		s.setPassword(rs.getString("PASSWORD"));
		s.setRegDate(rs.getTimestamp("REG_DATE"));
		return s;
	}
}
