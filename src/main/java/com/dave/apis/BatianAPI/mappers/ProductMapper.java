package com.dave.apis.BatianAPI.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.dave.apis.BatianAPI.core.Product;

public class ProductMapper implements ResultSetMapper<Product> {

	@Override
	public Product map(int index, ResultSet rs, StatementContext ctx) throws SQLException {

		Product p = new Product();
		p.setProductId(rs.getInt("PRODUCT_ID"));
		p.setName(rs.getString("NAME"));
		p.setDescription(rs.getString("DESCRIPTION"));
		p.setPrice(rs.getDouble("PRICE"));
		p.setCategoryId(rs.getInt("CATEGORY_ID"));
		p.setRegDate(rs.getTimestamp("REG_DATE"));
		return p;
	}
}
