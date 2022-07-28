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

import com.dave.apis.BatianAPI.core.Product;

import com.dave.apis.BatianAPI.dao.AppDAO;
import com.dave.apis.BatianAPI.dao.ProductDAO;

import com.dave.apis.BatianAPI.util.AppConstants;
public abstract class ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@CreateSqlObject
	abstract AppDAO appDAO();

	@CreateSqlObject
	abstract ProductDAO productDAO();

	public Product createProduct(Product product) {
		LOGGER.info("Creating Product ::::: \n" + product.toString());

		Product p = product;

		p.setProductId(appDAO().getUniqueIntegerItemID());
		productDAO().createProduct(p);
		return productDAO().getProduct(p.getProductId());
	}

	//Read a Single Product
	public Product getProduct(int productId) {
		LOGGER.info("Retrieving Product ::::: supplied ID: " + productId);

		Product p = productDAO().getProduct(productId);

		if(Objects.isNull(p)) {
			throw new WebApplicationException(String.format(AppConstants.ENTITY_NOT_FOUND.toString(), productId), Status.NOT_FOUND);
		}

		return p;
	}

	public List<Product> getProducts() {
		LOGGER.info("Retrieving all Products");

		return productDAO().getProducts();
	}

	//Update Product
	public Product editProduct(Product product ) {
		if(Objects.isNull(productDAO().getProduct(product.getProductId()))) {
			throw new WebApplicationException(String.format(AppConstants.ENTITY_NOT_FOUND.toString(), Status.NOT_FOUND));
		}
		productDAO().updateProduct(product);
		return productDAO().getProduct(product.getProductId());
	}

	//DELETE Product
	public String deleteProduct(final int productId) {
		int result = productDAO().deleteProduct(productId);
		LOGGER.info("Results after attempting delete via ProductService.deleteProduct(): ", result);

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
    		productDAO().getProducts();
    	} catch(UnableToObtainConnectionException ex) {
    		return AppService.checkUnableToObtainConnectionException(ex);
    	} catch(UnableToExecuteStatementException ex) {
    		return AppService.checkUnableToExecuteStatementException(ex);
    	}
    	return null;
    }

}
