package com.dave.apis.BatianAPI.core;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ProductCategory")
public class ProductCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="CategoryId of the ProductCategory", required = true, name = "categoryId")
	@JsonProperty
	private int categoryId;

	@ApiModelProperty(value="Category of the ProductCategory", required = true, name = "category")
	@JsonProperty
	private String category;

	@ApiModelProperty(value="Description of the ProductCategory", required = true, name = "description")
	@JsonProperty
	private String description;


	public ProductCategory() {
		super();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

