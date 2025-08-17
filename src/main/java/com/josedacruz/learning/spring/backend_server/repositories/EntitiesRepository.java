package com.josedacruz.learning.spring.backend_server.repositories;

import com.josedacruz.learning.spring.backend_server.domain.Entity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class EntitiesRepository extends JdbcGenericDao<Entity, Integer> {

    public EntitiesRepository(@Autowired @Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    protected RowMapper<Entity> getRowMapper() {
        return new BeanPropertyRowMapper<>(Entity.class);
    }

    @Override
    protected String getTableName() {
        return "entities";
    }

    @Override
    protected String getIdColumn() {
        return "id";
    }

    @Override
    protected List<String> getColumnNames() {
        return List.of("name");
    }

    @Override
    protected List<Object> getColumnValues(Entity entity) {
        return List.of(entity.getName());
    }

    // The ID is included as the last parameter for the update statement
    // in an UPDATE statement is usually the last parameter
    @Override
    protected List<Object> getUpdateValues(Entity entity) {
        return List.of(entity.getName(), entity.getId());
    }

    public Entity insertEntityAndReturnId(Entity entity) {
        Integer res = jdbcTemplate.execute("{call insert_entity(?, ?)}", (CallableStatementCallback<Integer>) cs -> {
            cs.setString(1, entity.getName());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            return cs.getInt(2);
        });

        entity.setId(res);
        return entity;
    }
}
