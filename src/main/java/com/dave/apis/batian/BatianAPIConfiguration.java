package com.dave.apis.batian;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

public class BatianAPIConfiguration extends Configuration {
    private static final String DATABASE = "database";
    
	@Valid
    @NotNull
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();
	
	@JsonProperty("swagger")
	public SwaggerBundleConfiguration swaggerBundleConfiguration;
	
    @JsonProperty(DATABASE)
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty(DATABASE)
    public void setDataSourceFactory(final DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }
}
