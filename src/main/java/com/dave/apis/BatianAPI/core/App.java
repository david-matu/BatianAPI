package com.dave.apis.BatianAPI.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="App")
public class App {
	
	@ApiModelProperty(value="This is a result of database check", required = false, name = "db_result")
	@JsonProperty
	private int databaseResult;

	public App() {
	}

	public int getDatabaseResult() {
		return databaseResult;
	}

	public void setDatabaseResult(int databaseResult) {
		this.databaseResult = databaseResult;
	}	
}

