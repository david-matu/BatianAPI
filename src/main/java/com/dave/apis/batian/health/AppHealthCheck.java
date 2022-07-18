package com.dave.apis.batian.health;

import com.codahale.metrics.health.HealthCheck;
import com.dave.apis.batian.service.EmployeeService;

public class AppHealthCheck extends HealthCheck {
	private static final String HEALTHY_MSG = "Application is healthy";
	private static final String UNHEALTHY_MSG = "(!)-(!) Warning: Problems with Application Health (!)";
	
	final EmployeeService employeeService;
	
	public AppHealthCheck(EmployeeService es) {
		this.employeeService = es;
	}

	@Override
	protected Result check() throws Exception {
		String dbHealthStatus = employeeService.performHealthCheck();
		
		if(dbHealthStatus == null) {
			return Result.healthy(HEALTHY_MSG);
		} else {
			return Result.unhealthy(UNHEALTHY_MSG, dbHealthStatus);
		}
	}

}
