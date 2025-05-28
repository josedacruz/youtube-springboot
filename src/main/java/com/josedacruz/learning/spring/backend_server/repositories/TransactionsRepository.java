package com.josedacruz.learning.spring.backend_server.repositories;

import com.josedacruz.learning.spring.backend_server.domain.Transaction;

import java.util.List;

public interface TransactionsRepository {
    List<Transaction> getAllTransactions();
}
