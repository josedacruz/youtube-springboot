package com.josedacruz.learning.spring.backend_server.configurations;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class H2DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:usersdb")
                .driverClassName("org.h2.Driver")
                .username("sa")
                .password("")
                .build();
    }
}
