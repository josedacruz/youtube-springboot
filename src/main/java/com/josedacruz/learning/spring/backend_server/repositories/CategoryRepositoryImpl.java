package com.josedacruz.learning.spring.backend_server.repositories;

import com.josedacruz.learning.spring.backend_server.domain.Category;
import com.josedacruz.learning.spring.backend_server.domain.Transaction;
import com.josedacruz.learning.spring.backend_server.dtos.CategoryWithTransactions;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;
    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
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

    // For educational purposes, this method is used to insert initial categories into the database.
    // the list should come from a configuration file or an external source in a real application.
    @PostConstruct
    public void insertCategories() {
        String sql = "INSERT INTO categories (name, type) VALUES (?, ?)";

        List<Category> categories = List.of(
                new Category("Groceries", "EXPENSE"),
                new Category("Utilities", "EXPENSE"),
                new Category("Rent", "EXPENSE"),
                new Category("Entertainment", "EXPENSE"),
                new Category("Salary", "INCOME"),
                new Category("Freelance", "INCOME"),
                new Category("Investment", "INCOME")
        );

        try {
            int[] result = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Category category = categories.get(i);
                    ps.setString(1, category.getName());
                    ps.setString(2, category.getType());
                }

                @Override
                public int getBatchSize() {
                    return categories.size();
                }
            });

            System.out.println("Batch insert completed. Rows affected per operation: " + Arrays.toString(result));
        } catch (Exception e) {
            System.err.println("Error during category batch insert: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @PostConstruct
    public void insertCategoriesDifferentApproach() {
        String sql = "INSERT INTO categories (name, type) VALUES (?, ?)";

        List<Category> categories = List.of(
                new Category("Method2-Groceries", "EXPENSE"),
                new Category("Method2-Utilities", "EXPENSE"),
                new Category("Method2-Rent", "EXPENSE"),
                new Category("Method2-Entertainment", "EXPENSE"),
                new Category("Method2-Salary", "INCOME"),
                new Category("Method2-Freelance", "INCOME"),
                new Category("Method2-Investment", "INCOME")
        );

        try {
            int[][] results = jdbcTemplate.batchUpdate(
                    sql,
                    categories,
                    3,
                    new ParameterizedPreparedStatementSetter<Category>() {
                        @Override
                        public void setValues(PreparedStatement ps, Category category) throws SQLException {
                            ps.setString(1, category.getName());
                            ps.setString(2, category.getType());
                        }
                    }
            );

            System.out.println("Batch insert completed.");
            for (int i = 0; i < results.length; i++) {
                System.out.println("Batch " + i + " results: " + Arrays.toString(results[i]));
            }
        } catch (Exception e) {
            System.err.println("Error during category batch insert: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
