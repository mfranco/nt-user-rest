package com.notempo1320.configuration;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;


public class AppConfiguration extends io.dropwizard.Configuration {
    private URI endpointUri = null;

    @NotNull
    @JsonProperty("endpoint")
    private  String endpoint;

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

	public URI getEndpoint() throws java.net.URISyntaxException {
        if (null == this.endpointUri) {
            this.endpointUri = new URI(this.endpoint);
        }
        return this.endpointUri;
    }
}
