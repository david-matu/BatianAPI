package com.dave.apis.BatianAPI.service;

import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.dave.apis.BatianAPI.dao.AppDAO;
import com.dave.apis.BatianAPI.util.AppConstants;

public abstract class AppService {
	
	@CreateSqlObject
    abstract AppDAO appDAO();
	
	public String performHealthCheck() {
    	try {
    		appDAO().testDBHealth();
    	} catch(UnableToObtainConnectionException ex) {
    		return checkUnableToObtainConnectionException(ex);
    	} catch(UnableToExecuteStatementException ex) {
    		return checkUnableToExecuteStatementException(ex);
    	}
    	return null;
    }
	
	public static String checkUnableToObtainConnectionException(UnableToObtainConnectionException ex) {
    	if(ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
    		return AppConstants.DATABASE_ACCESS_ERROR + ex.getCause().getLocalizedMessage();
    	} else if(ex.getCause() instanceof java.sql.SQLException) {
    		return AppConstants.DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
    	} else {
    		return AppConstants.UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
    	}
    }
    
    public static String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
    	if(ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
    		return AppConstants.DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
    	} else {
    		return AppConstants.UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
    	}
    }
}

