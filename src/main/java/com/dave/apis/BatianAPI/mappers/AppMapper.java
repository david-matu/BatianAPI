package com.dave.apis.BatianAPI.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.dave.apis.BatianAPI.core.App;

public class AppMapper implements ResultSetMapper<App> {

	@Override
	public App map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
		App c = new App();		
		c.setDatabaseResult(rs.getInt(1));		
		return c;
	}
}

