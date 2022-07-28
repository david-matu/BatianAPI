package com.dave.apis.BatianAPI.health;

import com.codahale.metrics.health.HealthCheck;
import com.dave.apis.BatianAPI.service.AppService;

public class AppHealthCheck extends HealthCheck {
	
	private static final String HEALTHY_MSG = "Application is healthy";
	private static final String UNHEALTHY_MSG = "(!)-(!) Warning: Problems with Application Health (!)";
	
	final AppService appService;
	
	public AppHealthCheck(AppService appService) {
		this.appService = appService;
	}

	@Override
	protected Result check() throws Exception {
		String dbHealthStatus = appService.performHealthCheck();
		
		if(dbHealthStatus == null) {
			return Result.healthy(HEALTHY_MSG);
		} else {
			return Result.unhealthy(UNHEALTHY_MSG, dbHealthStatus);
		}
	}
}

