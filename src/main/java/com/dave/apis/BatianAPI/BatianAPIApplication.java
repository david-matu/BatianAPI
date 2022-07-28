package com.dave.apis.BatianAPI;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;

import com.dave.apis.BatianAPI.health.AppHealthCheck;

import com.dave.apis.BatianAPI.resources.CustomerResource;
import com.dave.apis.BatianAPI.resources.ProductResource;
import com.dave.apis.BatianAPI.resources.ProductCategoryResource;
import com.dave.apis.BatianAPI.resources.StaffResource;

import com.dave.apis.BatianAPI.service.AppService;
import com.dave.apis.BatianAPI.service.CustomerService;
import com.dave.apis.BatianAPI.service.ProductService;
import com.dave.apis.BatianAPI.service.ProductCategoryService;
import com.dave.apis.BatianAPI.service.StaffService;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class BatianAPIApplication extends Application<BatianAPIConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BatianAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "BatianAPIAPI";
    }

    @Override
    public void initialize(final Bootstrap<BatianAPIConfiguration> bootstrap) {
    	bootstrap.addBundle(new SwaggerBundle<BatianAPIConfiguration>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(BatianAPIConfiguration config) {
				return config.swaggerBundleConfiguration;
			}
        });
    }

    @Override
    public void run(final BatianAPIConfiguration config, final Environment env) {
        final DataSource dataSource = config.getDataSourceFactory().build(env.metrics(), getName());
        
        DBI dbi = new DBI(dataSource);
        
        AppHealthCheck healthCheck = new AppHealthCheck(dbi.onDemand(AppService.class));
        env.healthChecks().register(getName(), healthCheck);

		env.jersey().register(getConfigurationClass());
		
		env.jersey().register(new CustomerResource(dbi.onDemand(CustomerService.class)));
		env.jersey().register(new ProductResource(dbi.onDemand(ProductService.class)));
		env.jersey().register(new ProductCategoryResource(dbi.onDemand(ProductCategoryService.class)));
		env.jersey().register(new StaffResource(dbi.onDemand(StaffService.class)));

    }

}

