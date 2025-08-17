package com.josedacruz.learning.spring.backend_server.configurations;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//@Component
public class MySqlInitializer {

    @Autowired
    @Qualifier("jdbcTemplateMySQL")
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS entities (
              id INT AUTO_INCREMENT PRIMARY KEY,
              name VARCHAR(255)
            );
        """);

        jdbcTemplate.execute("DROP PROCEDURE IF EXISTS insert_entity");

        jdbcTemplate.execute("""
            CREATE PROCEDURE insert_entity(IN p_name VARCHAR(255), OUT p_id INT)
            BEGIN
              INSERT INTO entities(name) VALUES (p_name);
              SET p_id = LAST_INSERT_ID();
            END;
        """);
    }
}
