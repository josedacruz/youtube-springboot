package com.josedacruz.learning.spring.backend_server.configurations;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;

//@Configuration
public class TestContainerMySQLConfig {

    static final MySQLContainer<?> mysql;

    static {
        mysql = new MySQLContainer<>("mysql:8")
                .withDatabaseName("financesdb")
                .withUsername("root")
                .withPassword("root");
        mysql.start();
    }

    @Bean({"mysqlDataSource"})
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(mysql.getJdbcUrl())
                .username(mysql.getUsername())
                .password(mysql.getPassword())
                .driverClassName(mysql.getDriverClassName())
                .build();
    }

    @Bean({"jdbcTemplateMySQL"})
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
