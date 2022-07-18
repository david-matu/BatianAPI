package com.dave.apis.batian.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.dave.apis.batian.core.Employee;

public class EmployeeMapper implements ResultSetMapper<Employee> {

	@Override
	public Employee map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
		Employee e = new Employee();
		
		e.setId(rs.getInt("ID"));
		e.setName(rs.getString("NAME"));
		e.setDepartment(rs.getString("DEPARTMENT"));
		e.setSalary(rs.getInt("SALARY"));
		
		return e;
	}
	
}
