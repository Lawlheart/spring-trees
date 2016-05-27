package com.lawlietblack.springtrees;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class AppConfig {
//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        // Create a StandardServiceRegistry
//        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
//    }
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
