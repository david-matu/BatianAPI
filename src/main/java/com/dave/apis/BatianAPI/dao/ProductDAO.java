package com.dave.apis.BatianAPI.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.dave.apis.BatianAPI.core.Product;
import com.dave.apis.BatianAPI.mappers.ProductMapper;

@RegisterMapper(ProductMapper.class)
public interface ProductDAO {

	@SqlQuery("SELECT * FROM PRODUCT")
	abstract List<Product> getProducts();

	@SqlQuery("SELECT * FROM PRODUCT WHERE PRODUCT_ID = :productId")
	public Product getProduct(@Bind("productId") final int productId);

	@SqlUpdate("INSERT INTO PRODUCT(PRODUCT_ID, NAME, DESCRIPTION, PRICE, CATEGORY_ID, REG_DATE ) values(:productId, :name, :description, :price, :categoryId, :regDate )")
	void createProduct(@BindBean final Product product);

	@SqlUpdate("UPDATE PRODUCT SET PRODUCT_ID = coalesce(:productId, PRODUCT_ID), NAME = coalesce(:name, NAME), DESCRIPTION = coalesce(:description, DESCRIPTION), PRICE = coalesce(:price, PRICE), CATEGORY_ID = coalesce(:categoryId, CATEGORY_ID), REG_DATE = coalesce(:regDate, REG_DATE) WHERE PRODUCT_ID = :productId ")
	void updateProduct(@BindBean final Product product);

	@SqlUpdate("DELETE FROM PRODUCT WHERE PRODUCT_ID = :productId")
	int deleteProduct(@Bind("productId") final int productId);

	}
