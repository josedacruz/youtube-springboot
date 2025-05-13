package com.josedacruz.learning.spring.backend_server.configurations;

import com.josedacruz.learning.spring.backend_server.domain.Entity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class RepositoryHelpersConfiguration {

    @Bean
    public RowMapper<Entity> entityRowMapper() {
        return (rs, rowNum) -> new Entity(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

}
