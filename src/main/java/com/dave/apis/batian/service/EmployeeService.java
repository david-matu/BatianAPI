package com.dave.apis.batian.service;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dave.apis.batian.core.Employee;
import com.dave.apis.batian.dao.EmployeeDAO;
import com.dave.apis.batian.mappers.EmployeeMapper;

public abstract class EmployeeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	private static final String DATABASE_ACCESS_ERROR = "Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
	private static final String DATABASE_CONNECTION_ERROR = "Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
    private static final String UNEXPECTED_DATABASE_ERROR = "Unexpected error occurred while attempting to reach the database. Details: ";
    private static final String SUCCESS = "Success";
    private static final String UNEXPECTED_DELETE_ERROR = "An unexpected error occurred while deleting employee.";
    private static final String EMPLOYEE_NOT_FOUND = "Employee id %s not found.";
    
    @CreateSqlObject
    abstract EmployeeDAO employeeDAO();
    
    public Employee createEmployee(Employee employee) {
    	LOGGER.info("Creating an Employee as invoked: \n" + employee.toString());
    	Employee e = employee;
    	e.setId(employeeDAO().lastInsertID());
    	employeeDAO().createEmployee(e);
    	return employeeDAO().getEmployee(employeeDAO().lastInsertID());
    }
    
    public Employee getEmployee(int id) {
    	LOGGER.info("Retrieving an Employee by ID " + id);
    	Employee e = employeeDAO().getEmployee(id);
    	
    	if(Objects.isNull(e)) {
    		throw new WebApplicationException(String.format(EMPLOYEE_NOT_FOUND, id), Status.NOT_FOUND);
    	}
    	
    	return e;
    }
    
    public List<Employee> getEmployees() {
    	LOGGER.info("Retrieving all Employees");
    	
    	return employeeDAO().getEmployees();
    }
    
    public Employee editEmployee(Employee e) {
    	if(Objects.isNull(employeeDAO().getEmployee(e.getId()))) {
    		throw new WebApplicationException(String.format(EMPLOYEE_NOT_FOUND, Status.NOT_FOUND));
    	}
    	employeeDAO().editEmployee(e);
    	return employeeDAO().getEmployee(e.getId());
    }
    
    public String deleteEmployee(final int id) {
    	int result = employeeDAO().deleteEmployee(id);
    	LOGGER.info("Results after attempting delete via EmployeeService.deleteEmployee(): ", result);
    	
    	switch(result) {
    	case 1:
    		return SUCCESS;
    	case 0:
    		throw new WebApplicationException(String.format(EMPLOYEE_NOT_FOUND, Status.NOT_FOUND));
    		
    		default:
    			throw new WebApplicationException(String.format(UNEXPECTED_DELETE_ERROR, Status.INTERNAL_SERVER_ERROR));
    	}
    }
    
    public String performHealthCheck() {
    	try {
    		employeeDAO().getEmployees();
    	} catch(UnableToObtainConnectionException ex) {
    		return checkUnableToObtainConnectionException(ex);
    	} catch(UnableToExecuteStatementException ex) {
    		return checkUnableToExecuteStatementException(ex);
    	}
    	return null;
    }
    
    private String checkUnableToObtainConnectionException(UnableToObtainConnectionException ex) {
    	if(ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
    		return DATABASE_ACCESS_ERROR + ex.getCause().getLocalizedMessage();
    	} else if(ex.getCause() instanceof java.sql.SQLException) {
    		return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
    	} else {
    		return UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
    	}
    }
    
    private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
    	if(ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
    		return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
    	} else {
    		return UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
    	}
    }
}
