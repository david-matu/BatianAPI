package com.dave.apis.batian.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Employee")
public class Employee {
	
	@ApiModelProperty(value="This ID of the staff", required = true, name = "id")
	@JsonProperty
	private Integer id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String department;
	
	@JsonProperty
	private int salary;

	/**
	 * 
	 */
	public Employee() {
		super();
	}

	public Employee(String name, String department, int salary) {
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee {id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + "}";
	}

}
