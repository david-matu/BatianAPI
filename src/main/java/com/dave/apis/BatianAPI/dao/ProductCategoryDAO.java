package com.dave.apis.BatianAPI.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.dave.apis.BatianAPI.core.ProductCategory;
import com.dave.apis.BatianAPI.mappers.ProductCategoryMapper;

@RegisterMapper(ProductCategoryMapper.class)
public interface ProductCategoryDAO {

	@SqlQuery("SELECT * FROM PRODUCT_CATEGORY")
	abstract List<ProductCategory> getProductCategorys();

	@SqlQuery("SELECT * FROM PRODUCT_CATEGORY WHERE CATEGORY_ID = :categoryId")
	public ProductCategory getProductCategory(@Bind("categoryId") final int categoryId);

	@SqlUpdate("INSERT INTO PRODUCT_CATEGORY(CATEGORY_ID, CATEGORY, DESCRIPTION ) values(:categoryId, :category, :description )")
	void createProductCategory(@BindBean final ProductCategory productcategory);

	@SqlUpdate("UPDATE PRODUCT_CATEGORY SET CATEGORY_ID = coalesce(:categoryId, CATEGORY_ID), CATEGORY = coalesce(:category, CATEGORY), DESCRIPTION = coalesce(:description, DESCRIPTION) WHERE CATEGORY_ID = :categoryId ")
	void updateProductCategory(@BindBean final ProductCategory productcategory);

	@SqlUpdate("DELETE FROM PRODUCT_CATEGORY WHERE CATEGORY_ID = :categoryId")
	int deleteProductCategory(@Bind("categoryId") final int categoryId);

	}
