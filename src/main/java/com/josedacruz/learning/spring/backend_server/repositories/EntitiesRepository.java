package com.josedacruz.learning.spring.backend_server.repositories;

import com.josedacruz.learning.spring.backend_server.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EntitiesRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EntitiesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // This method should throw SQLException but due to spring boot it will throw BadSqlGrammarException
    public Optional<Entity> findById(int id)  {
        String sql = "SELECT id, name FROM entities WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, entityMapper(), id));
    }

    private RowMapper<Entity> entityMapper() {
        return (rs, rowNum) -> new Entity(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
}
