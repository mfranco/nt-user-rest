package com.notempo1320.utils.db;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;


public class TestDAO {
    private static AnnotationConfiguration config =
        new AnnotationConfiguration();

    public static AnnotationConfiguration getConfig() {
        config.setProperty("hibernate.connection.url", "jdbc:h2:test");
        config.setProperty("hibernate.connection.driver_class",
            "org.h2.Driver");
        config.setProperty("hibernate.current_session_context_class",
            "thread");
        config.setProperty("hibernate.show_sql", "false");
        config.setProperty("hibernate.hbm2ddl.auto", "create");

        return config;
    }

}
