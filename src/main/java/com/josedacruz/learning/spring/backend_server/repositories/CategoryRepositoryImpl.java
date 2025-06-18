package com.josedacruz.learning.spring.backend_server.repositories;

import com.josedacruz.learning.spring.backend_server.domain.Category;
import com.josedacruz.learning.spring.backend_server.domain.Transaction;
import com.josedacruz.learning.spring.backend_server.dtos.CategoryWithTransactions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;
    public CategoryRepositoryImpl(@Qualifier("jdbcTemplateH2") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT id, name, type FROM categories";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Category(rs.getInt("id"), rs.getString("name"), rs.getString("type")));
    }

    @Override
    public CategoryWithTransactions findTransactionsByCategoryId(int categoryId) {
        String sql = """
                        SELECT 
                            c.id AS c_id, c.name AS c_name, c.type AS c_type,
                            t.id AS t_id, t.user_id, t.entity_id, t.category_id,
                            t.date, t.amount, t.description, t.type AS t_type, t.payment_method
                        FROM categories c
                        LEFT JOIN transactions t ON c.id = t.category_id
                        WHERE c.id = ?
                    """;

        Map<Integer, CategoryWithTransactions> map = new LinkedHashMap<>();

        jdbcTemplate.query(sql, new Object[]{categoryId}, rs -> {
            int catId = rs.getInt("c_id");

            CategoryWithTransactions catWithTx = map.computeIfAbsent(catId, id -> {
                Category category = null;
                try {
                    category = new Category(
                            id,
                            rs.getString("c_name"),
                            rs.getString("c_type")
                    );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return new CategoryWithTransactions(category, new ArrayList<>());
            });

            int txId = rs.getInt("t_id");
            if (!rs.wasNull()) {
                Transaction tx = new Transaction(
                        txId,
                        rs.getInt("user_id"),
                        rs.getObject("entity_id") != null ? rs.getInt("entity_id") : null,
                        rs.getInt("category_id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getBigDecimal("amount"),
                        rs.getString("description"),
                        rs.getString("t_type"),
                        rs.getString("payment_method")
                );
                catWithTx.getTransactions().add(tx);
            }
        });

        return map.get(categoryId);
    }

    @Override
    public Category save(Category category) {
        String sql = "INSERT INTO categories (name, type) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            ps.setString(2, category.getType());
            return ps;
        }, keyHolder);

        category.setId(keyHolder.getKey().intValue());
        return category;
    }

}
