package com.dave.apis.batian.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.dave.apis.batian.core.Employee;
import com.dave.apis.batian.mappers.EmployeeMapper;

@RegisterMapper(EmployeeMapper.class)
public interface EmployeeDAO {
	
	@SqlQuery("SELECT * FROM EMPLOYEE")
	abstract List<Employee> getEmployees();
	
	@SqlQuery("SELECT * FROM EMPLOYEE WHERE ID = :id")
	public Employee getEmployee(@Bind("id") final int id);
	
	@SqlUpdate("INSERT INTO EMPLOYEE(NAME, DEPARTMENT, SALARY) "
			+ "values(:name, :department, :salary)")
	void createEmployee(@BindBean final Employee Employee);
	
	@SqlUpdate("UPDATE EMPLOYEE SET ID = coalesce(:id, ID), NAME = coalesce(:name, NAME), "
			+ "DEPARTMENT = coalesce(:department, DEPARTMENT), SALARY = coalesce(:salary, SALARY) "
			+ "WHERE ID= :id")
	void editEmployee(@BindBean final Employee Employee);
	
	@SqlQuery("delete from EMPLOYEE where ID = :id")
	int deleteEmployee(@Bind("id") final int id);
	
	@SqlQuery("select last_insert_id();")
	public int lastInsertID();
}
