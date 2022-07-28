package com.dave.apis.BatianAPI.core;

import java.io.Serializable;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="ProductId of the Product", required = true, name = "productId")
	@JsonProperty
	private int productId;

	@ApiModelProperty(value="Name of the Product", required = true, name = "name")
	@JsonProperty
	private String name;

	@ApiModelProperty(value="Description of the Product", required = true, name = "description")
	@JsonProperty
	private String description;

	@ApiModelProperty(value="Price of the Product", required = true, name = "price")
	@JsonProperty
	private Double price;

	@ApiModelProperty(value="CategoryId of the Product", required = true, name = "categoryId")
	@JsonProperty
	private int categoryId;

	@ApiModelProperty(value="RegDate of the Product", required = true, name = "regDate")
	@JsonProperty
	private Timestamp regDate;


	public Product() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}

