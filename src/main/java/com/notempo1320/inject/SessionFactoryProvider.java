package com.notempo1320.inject;

import com.notempo1320.configuration.AppConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Guice Provider for Hibernate session factory.
 *
 */
public class SessionFactoryProvider implements Provider<SessionFactory> {

	private final HibernateBundle<AppConfiguration> hibernateBundle;

	@Inject
	public SessionFactoryProvider(HibernateBundle<AppConfiguration>
        hibernateBundle) {
		this.hibernateBundle = hibernateBundle;
	}

	@Override
	public SessionFactory get() {
		return hibernateBundle.getSessionFactory();
	}
}
