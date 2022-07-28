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

import com.dave.apis.BatianAPI.core.ProductCategory;

import com.dave.apis.BatianAPI.dao.AppDAO;
import com.dave.apis.BatianAPI.dao.ProductCategoryDAO;

import com.dave.apis.BatianAPI.util.AppConstants;
public abstract class ProductCategoryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryService.class);

	@CreateSqlObject
	abstract AppDAO appDAO();

	@CreateSqlObject
	abstract ProductCategoryDAO productcategoryDAO();

	public ProductCategory createProductCategory(ProductCategory productcategory) {
		LOGGER.info("Creating ProductCategory ::::: \n" + productcategory.toString());

		ProductCategory p = productcategory;

		p.setCategoryId(appDAO().getUniqueIntegerItemID());
		productcategoryDAO().createProductCategory(p);
		return productcategoryDAO().getProductCategory(p.getCategoryId());
	}

	//Read a Single ProductCategory
	public ProductCategory getProductCategory(int categoryId) {
		LOGGER.info("Retrieving ProductCategory ::::: supplied ID: " + categoryId);

		ProductCategory p = productcategoryDAO().getProductCategory(categoryId);

		if(Objects.isNull(p)) {
			throw new WebApplicationException(String.format(AppConstants.ENTITY_NOT_FOUND.toString(), categoryId), Status.NOT_FOUND);
		}

		return p;
	}

	public List<ProductCategory> getProductCategorys() {
		LOGGER.info("Retrieving all ProductCategorys");

		return productcategoryDAO().getProductCategorys();
	}

	//Update ProductCategory
	public ProductCategory editProductCategory(ProductCategory productcategory ) {
		if(Objects.isNull(productcategoryDAO().getProductCategory(productcategory.getCategoryId()))) {
			throw new WebApplicationException(String.format(AppConstants.ENTITY_NOT_FOUND.toString(), Status.NOT_FOUND));
		}
		productcategoryDAO().updateProductCategory(productcategory);
		return productcategoryDAO().getProductCategory(productcategory.getCategoryId());
	}

	//DELETE ProductCategory
	public String deleteProductCategory(final int categoryId) {
		int result = productcategoryDAO().deleteProductCategory(categoryId);
		LOGGER.info("Results after attempting delete via ProductCategoryService.deleteProductCategory(): ", result);

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
    		productcategoryDAO().getProductCategorys();
    	} catch(UnableToObtainConnectionException ex) {
    		return AppService.checkUnableToObtainConnectionException(ex);
    	} catch(UnableToExecuteStatementException ex) {
    		return AppService.checkUnableToExecuteStatementException(ex);
    	}
    	return null;
    }

}
