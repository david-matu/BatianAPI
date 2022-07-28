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

import com.dave.apis.BatianAPI.core.Staff;

import com.dave.apis.BatianAPI.dao.AppDAO;
import com.dave.apis.BatianAPI.dao.StaffDAO;

import com.dave.apis.BatianAPI.util.AppConstants;
public abstract class StaffService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffService.class);

	@CreateSqlObject
	abstract AppDAO appDAO();

	@CreateSqlObject
	abstract StaffDAO staffDAO();

	public Staff createStaff(Staff staff) {
		LOGGER.info("Creating Staff ::::: \n" + staff.toString());

		Staff s = staff;

		s.setUserId(appDAO().getUniqueIntegerItemID());
		staffDAO().createStaff(s);
		return staffDAO().getStaff(s.getUserId());
	}

	//Read a Single Staff
	public Staff getStaff(long userId) {
		LOGGER.info("Retrieving Staff ::::: supplied ID: " + userId);

		Staff s = staffDAO().getStaff(userId);

		if(Objects.isNull(s)) {
			throw new WebApplicationException(String.format(AppConstants.ENTITY_NOT_FOUND.toString(), userId), Status.NOT_FOUND);
		}

		return s;
	}

	public List<Staff> getStaffs() {
		LOGGER.info("Retrieving all Staffs");

		return staffDAO().getStaffs();
	}

	//Update Staff
	public Staff editStaff(Staff staff ) {
		if(Objects.isNull(staffDAO().getStaff(staff.getUserId()))) {
			throw new WebApplicationException(String.format(AppConstants.ENTITY_NOT_FOUND.toString(), Status.NOT_FOUND));
		}
		staffDAO().updateStaff(staff);
		return staffDAO().getStaff(staff.getUserId());
	}

	//DELETE Staff
	public String deleteStaff(final long userId) {
		int result = staffDAO().deleteStaff(userId);
		LOGGER.info("Results after attempting delete via StaffService.deleteStaff(): ", result);

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
    		staffDAO().getStaffs();
    	} catch(UnableToObtainConnectionException ex) {
    		return AppService.checkUnableToObtainConnectionException(ex);
    	} catch(UnableToExecuteStatementException ex) {
    		return AppService.checkUnableToExecuteStatementException(ex);
    	}
    	return null;
    }

}
