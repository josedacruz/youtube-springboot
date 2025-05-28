package com.josedacruz.learning.spring.backend_server.repositories;

import com.josedacruz.learning.spring.backend_server.domain.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionsRepositoryImpl implements TransactionsRepository {

    private final JdbcTemplate jdbcTemplate;
    public TransactionsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transaction> getAllTransactions() {
        String sql = """
                SELECT
                    id,
                    user_id,
                    entity_id,
                    category_id,
                    date,
                    amount,
                    description,
                    type,
                    payment_method
                FROM transactions
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Transaction(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("entity_id"),
                rs.getInt("category_id"),
                rs.getDate("date").toLocalDate(),
                rs.getBigDecimal("amount"),
                rs.getString("description"),
                rs.getString("type"),
                rs.getString("payment_method")
        ));
    }
}
