package com.josedacruz.learning.spring.backend_server.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public abstract class JdbcGenericDao<T,ID> implements GenericDao<T,ID>{
    protected final JdbcTemplate jdbcTemplate;

    protected JdbcGenericDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    protected abstract RowMapper<T> getRowMapper();
    protected abstract String getTableName();
    protected abstract String getIdColumn();
    protected abstract List<String> getColumnNames();
    protected abstract List<Object> getColumnValues(T entity);
    protected abstract List<Object> getUpdateValues(T entity);

    @Override
    public List<T> findAll() {
        return jdbcTemplate.query(
                "SELECT " + getIdColumn() + "," + String.join(", ", getColumnNames()) + " FROM " + getTableName(),
                getRowMapper()
        );
    }

    @Override
    public Optional<T> findById(ID id) {
        String sql = "SELECT " + getIdColumn() + "," + String.join(", ", getColumnNames()) + " FROM " + getTableName() + " WHERE " + getIdColumn() + " = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, getRowMapper(), id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public T save(T entity) {
        String sql = "INSERT INTO " + getTableName() + " (" + String.join(", ", getColumnNames()) + ") VALUES (" + String.join(", ", getColumnNames().stream().map(c -> "?").toList()) + ")";
        jdbcTemplate.update(sql, getColumnValues(entity).toArray());
        return entity; // In a real scenario, you might want to return the saved entity with its ID
    }

    @Override
    public void deleteById(ID id) {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE " + getIdColumn() + " = ?", id);
    }

    @Override
    public void update(T entity) {
        String sql = "UPDATE " + getTableName() + " SET " + String.join(" = ?, ", getColumnNames()) + " = ? WHERE " + getIdColumn() + " = ?";
        List<Object> values = getUpdateValues(entity);
        jdbcTemplate.update(sql, values.toArray());
    }
}
