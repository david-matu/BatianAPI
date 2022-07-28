package com.dave.apis.BatianAPI.core;

import java.io.Serializable;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="UserId of the Customer", required = true, name = "userId")
	@JsonProperty
	private long userId;

	@ApiModelProperty(value="FirstName of the Customer", required = true, name = "firstName")
	@JsonProperty
	private String firstName;

	@ApiModelProperty(value="LastName of the Customer", required = true, name = "lastName")
	@JsonProperty
	private String lastName;

	@ApiModelProperty(value="Phone of the Customer", required = true, name = "phone")
	@JsonProperty
	private String phone;

	@ApiModelProperty(value="Email of the Customer", required = true, name = "email")
	@JsonProperty
	private String email;

	@ApiModelProperty(value="Password of the Customer", required = true, name = "password")
	@JsonProperty
	private String password;

	@ApiModelProperty(value="RegDate of the Customer", required = true, name = "regDate")
	@JsonProperty
	private Timestamp regDate;


	public Customer() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}

