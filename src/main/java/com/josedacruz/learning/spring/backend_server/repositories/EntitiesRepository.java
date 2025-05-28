package com.josedacruz.learning.spring.backend_server.repositories;

import com.josedacruz.learning.spring.backend_server.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EntitiesRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Entity> entityRowMapper;

    @Autowired
    public EntitiesRepository(JdbcTemplate jdbcTemplate, RowMapper<Entity> entityMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityRowMapper = entityMapper;
    }


    // This method should throw SQLException but due to spring boot it will throw BadSqlGrammarException
    public Optional<Entity> findById(int id)  {
        String sql = "SELECT id, name FROM entities WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, entityRowMapper, id));
    }

    public List<Entity> findAll() {
        String sql = "SELECT id, name FROM entities";
        return jdbcTemplate.query(sql, entityRowMapper);
    }
}
