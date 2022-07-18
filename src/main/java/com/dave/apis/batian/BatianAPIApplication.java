package com.dave.apis.batian;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dave.apis.batian.health.AppHealthCheck;
import com.dave.apis.batian.resources.EmployeeResource;
import com.dave.apis.batian.service.EmployeeService;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.swagger.config.ScannerFactory;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.DefaultJaxrsScanner;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

//import com.mysql.cj.jdbc.Driver;	//Testing if Driver is correctly resolved

public class BatianAPIApplication extends Application<BatianAPIConfiguration> {
	private static final Logger logger = LoggerFactory.getLogger(BatianAPIApplication.class);
	
	private static final String APP_SERVICE = "Batian Shop API Service";
	
    public static void main(final String[] args) throws Exception {
    	logger.info("\n:::::::::::::::::::::::::::::Batian API Starting:::::::::::::::::::::::::::::");
        new BatianAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "sql";	//"BatianAPI + SQL";
    }

    @Override
    public void initialize(final Bootstrap<BatianAPIConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<BatianAPIConfiguration>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(BatianAPIConfiguration configuration) {
				
				return configuration.swaggerBundleConfiguration;
			}
        	
        });
    }

    @Override
    public void run(final BatianAPIConfiguration config, final Environment env) {
        final DataSource dataSource = config.getDataSourceFactory().build(env.metrics(), getName());
        
        DBI dbi = new DBI(dataSource);
        
        AppHealthCheck healthCheck = new AppHealthCheck(dbi.onDemand(EmployeeService.class));
        
        //Register the HealthCheck
        env.healthChecks().register(APP_SERVICE, healthCheck);
        logger.info("Registering RESTful resources for Batian API");
        env.jersey().register(getConfigurationClass());
        env.jersey().register(new EmployeeResource(dbi.onDemand(EmployeeService.class)));        
    }

}
