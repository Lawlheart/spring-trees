package com.lawlietblack.springtrees.configuration;

import com.lawlietblack.springtrees.model.Marriage;
import com.lawlietblack.springtrees.model.Person;
import com.lawlietblack.springtrees.model.Tree;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.lawlietblack.springtrees.model"})
@EnableJpaRepositories(basePackages = {"com.lawlietblack.springtrees.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/api");
                config.setDefaultMediaType(MediaType.APPLICATION_JSON);
                config.setReturnBodyOnCreate(Boolean.TRUE);
                config.setReturnBodyOnUpdate(Boolean.TRUE);
                config.exposeIdsFor(Person.class);
                config.exposeIdsFor(Marriage.class);
                config.exposeIdsFor(Tree.class);
            }
        };
    }
}
