package com.notempo1320.inject;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.notempo1320.configuration.AppConfiguration;
import com.notempo1320.db.GenericDAO;
import com.notempo1320.db.hibernate.PersonHibernateDAO;
import com.notempo1320.db.hibernate.UserHibernateDAO;
import com.notempo1320.facade.BaseFacade;
import com.notempo1320.facade.PersonFacade;
import com.notempo1320.facade.UserFacade;
import com.notempo1320.model.Person;
import com.notempo1320.model.User;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;
import java.util.concurrent.ForkJoinPool;

/**
 * Dependency Injection Configuration
 */

public class AppModule extends AbstractModule {

	private final HibernateBundle<AppConfiguration> hibernateBundle;

	public AppModule(HibernateBundle<AppConfiguration> hibernateBundle) {
		this.hibernateBundle = hibernateBundle;
	}

	@Override
	protected void configure() {
		bind(SessionFactory.class).toProvider(SessionFactoryProvider.class);
        bind(new TypeLiteral<GenericDAO<Person>>(){}).
            to(PersonHibernateDAO.class);
        bind(new TypeLiteral<GenericDAO<User>>(){})
            .to(UserHibernateDAO.class);
        bind(new TypeLiteral<BaseFacade<User>>(){})
            .to(UserFacade.class);
        bind(new TypeLiteral<BaseFacade<Person>>(){})
            .to(PersonFacade.class);
	}

	@Provides @Singleton
	private HibernateBundle<AppConfiguration> provideBundle() {
		return hibernateBundle;
	}
}
