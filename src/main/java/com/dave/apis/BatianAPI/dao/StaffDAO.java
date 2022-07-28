package com.dave.apis.BatianAPI.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.dave.apis.BatianAPI.core.Staff;
import com.dave.apis.BatianAPI.mappers.StaffMapper;

@RegisterMapper(StaffMapper.class)
public interface StaffDAO {

	@SqlQuery("SELECT * FROM STAFF")
	abstract List<Staff> getStaffs();

	@SqlQuery("SELECT * FROM STAFF WHERE USER_ID = :userId")
	public Staff getStaff(@Bind("userId") final long userId);

	@SqlUpdate("INSERT INTO STAFF(USER_ID, FIRST_NAME, LAST_NAME, PHONE, EMAIL, PASSWORD, REG_DATE ) values(:userId, :firstName, :lastName, :phone, :email, :password, :regDate )")
	void createStaff(@BindBean final Staff staff);

	@SqlUpdate("UPDATE STAFF SET USER_ID = coalesce(:userId, USER_ID), FIRST_NAME = coalesce(:firstName, FIRST_NAME), LAST_NAME = coalesce(:lastName, LAST_NAME), PHONE = coalesce(:phone, PHONE), EMAIL = coalesce(:email, EMAIL), PASSWORD = coalesce(:password, PASSWORD), REG_DATE = coalesce(:regDate, REG_DATE) WHERE USER_ID = :userId ")
	void updateStaff(@BindBean final Staff staff);

	@SqlUpdate("DELETE FROM STAFF WHERE USER_ID = :userId")
	int deleteStaff(@Bind("userId") final long userId);

	}
