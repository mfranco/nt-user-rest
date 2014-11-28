package com.notempo1320.cli;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import com.google.common.base.Optional;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.notempo1320.inject.AppModule;
import com.notempo1320.configuration.AppConfiguration;
import com.notempo1320.db.GenericDAO;
import com.notempo1320.db.hibernate.HibernateConfig;
import com.notempo1320.db.hibernate.UserHibernateDAO;
import com.notempo1320.facade.UserFacade;
import com.notempo1320.model.User;
import org.hibernate.SessionFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import java.io.*;
import java.util.List;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListUserCommand extends ConfiguredCommand<AppConfiguration> {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(ListUserCommand.class);

    private GuiceBundle<AppConfiguration> guiceBundle;

    public ListUserCommand() {
        super("list_users", "List all admin users");


    }

    @Override
    public void configure(Subparser subparser) {
        super.configure(subparser);
    }


    @Override
    protected void run(Bootstrap<AppConfiguration> bootstrap,
                       Namespace namespace,
                       AppConfiguration configuration) {
        AnnotationConfiguration dbConfig = null;
        SessionFactory factory = null;
        try {
            dbConfig = HibernateConfig.getConfig(configuration);
            dbConfig.addAnnotatedClass(User.class);
            factory = dbConfig.buildSessionFactory();
            final GenericDAO<User> dao =
                new UserHibernateDAO(factory);
            final UserFacade facade = new UserFacade(dao);


            factory.getCurrentSession().beginTransaction();
            System.out.print("\n Admin User List \n");

            List<User> users = facade.findByParams(
                Optional.fromNullable(null));
            StringWriter writer = new StringWriter();
            bootstrap.getObjectMapper().writeValue(writer, users);
            System.out.print(writer.toString());
            System.out.print("\n");
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
            System.exit(1);
        }

    }

}
