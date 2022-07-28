package com.dave.apis.BatianAPI.util;


public enum AppConstants {
	DATABASE_ACCESS_ERROR("Could not reach the database. The database may be down or there may be network connectivity issues. Details: "),
	DATABASE_CONNECTION_ERROR("Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: "),
    UNEXPECTED_DATABASE_ERROR("Unexpected error occurred while attempting to reach the database. Details: "),
    SUCCESS("Success"),
    UNEXPECTED_DELETE_ERROR("An unexpected error occurred while deleting entity."),
    ENTITY_NOT_FOUND("Entity with id %s not found.");
	
	private final String text;
	
	AppConstants(final String text) {
		this.text = text;
	}
    
	@Override
	public String toString() {
		return text;
	}
}

