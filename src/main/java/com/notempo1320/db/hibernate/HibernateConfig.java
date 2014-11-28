package com.notempo1320.db.hibernate;

import com.notempo1320.configuration.AppConfiguration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;


public class HibernateConfig {
    private static DataSourceFactory dataSourceFactory;

    private static AnnotationConfiguration dbConfig =
        new AnnotationConfiguration();

    public static AnnotationConfiguration getConfig(AppConfiguration config) {
        HibernateConfig.dataSourceFactory = config.getDataSourceFactory();
        dbConfig.setProperty("hibernate.connection.url",
            HibernateConfig.dataSourceFactory.getUrl());
        dbConfig.setProperty("hibernate.connection.driver_class",
            HibernateConfig.dataSourceFactory.getDriverClass());
        dbConfig.setProperty("hibernate.current_session_context_class",
            "thread");
        dbConfig.setProperty("hibernate.show_sql", "false");
        dbConfig.setProperty("hibernate.hbm2ddl.auto", "update");
        return dbConfig;

    }
}
