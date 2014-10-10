package com.notempo1320;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.google.common.base.Optional;
import com.notempo1320.cli.CreateUserCommand;
import com.notempo1320.inject.AppModule;
import com.notempo1320.configuration.AppConfiguration;
import com.notempo1320.db.GenericDAO;
import com.notempo1320.db.hibernate.PersonHibernateDAO;
import com.notempo1320.model.Person;
import com.notempo1320.model.User;
import com.notempo1320.resource.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.auth.basic.BasicAuthProvider;

public class App extends Application<AppConfiguration> {

    private final HibernateBundle<AppConfiguration> hibernate;
    private final GuiceBundle<AppConfiguration> guiceBundle;

    public App() {
        this.hibernate= new HibernateBundle<AppConfiguration>(
            Person.class, User.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(
                AppConfiguration configuration) {
                return configuration.getDataSourceFactory();

            }
        };
		this.guiceBundle = GuiceBundle
				.<AppConfiguration>newBuilder()
				.setConfigClass(AppConfiguration.class)
				.addModule(new AppModule(hibernate))
				.build();

    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

		bootstrap.getObjectMapper().registerModule(new MrBeanModule());
		bootstrap.getObjectMapper()
				.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        bootstrap.addBundle(this.guiceBundle);
        bootstrap.addBundle(this.hibernate);
        bootstrap.addCommand(new CreateUserCommand());



    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
       environment.jersey().register(PersonResource.class);

		environment.jersey().register(new BasicAuthProvider<>(basicCredentials -> {
			try {
				//UUID key = UUID.fromString(basicCredentials.getUsername());
				return Optional.of(new User());
			} catch (IllegalArgumentException iex) {
				//LOGGER.error("Couldn't authenticate user - wrong arguments", iex);
				return Optional.absent();
			}
		}, "nt-user"));
	

    }

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}
