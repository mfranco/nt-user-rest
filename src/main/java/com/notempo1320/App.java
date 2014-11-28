package com.notempo1320;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.google.common.base.Optional;
import com.notempo1320.auth.SimpleAuthenticator;
import com.notempo1320.cli.CreateUserCommand;
import com.notempo1320.cli.ListUserCommand;
import com.notempo1320.configuration.AppConfiguration;
import com.notempo1320.db.GenericDAO;
import com.notempo1320.db.hibernate.UserHibernateDAO;
import com.notempo1320.db.hibernate.PersonHibernateDAO;
import com.notempo1320.facade.BaseFacade;
import com.notempo1320.facade.PersonFacade;
import com.notempo1320.facade.UserFacade;
import com.notempo1320.inject.AppModule;
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
//    private final GuiceBundle<AppConfiguration> guiceBundle;

    public App() {
        this.hibernate= new HibernateBundle<AppConfiguration>(
            Person.class, User.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(
                AppConfiguration configuration) {
                return configuration.getDataSourceFactory();

            }
        };
//		this.guiceBundle = GuiceBundle
//				.<AppConfiguration>newBuilder()
//				.setConfigClass(AppConfiguration.class)
//				.addModule(new AppModule(hibernate))
//				.build();

    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

		bootstrap.getObjectMapper().registerModule(new MrBeanModule());
		bootstrap.getObjectMapper()
				.setPropertyNamingStrategy(
                    PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        bootstrap.addBundle(this.hibernate);
        //bootstrap.addBundle(this.guiceBundle);
        bootstrap.addCommand(new CreateUserCommand());
        bootstrap.addCommand(new ListUserCommand());


    }

    @Override
    public void run(AppConfiguration configuration, Environment environment)
        throws Exception {

        final GenericDAO<User> userDao =
                new UserHibernateDAO(this.hibernate.getSessionFactory());
        final BaseFacade<User> userFacade = new UserFacade(userDao);

        final GenericDAO<Person> dao =
                new PersonHibernateDAO(this.hibernate.getSessionFactory());
        final BaseFacade<Person> personFacade = new  PersonFacade(dao);

        environment.jersey().register(
            new PersonResource(personFacade, configuration)
        );

        environment.jersey().register(
            new BasicAuthProvider<>(new SimpleAuthenticator(userFacade), "ntrest"));


    }
	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}
