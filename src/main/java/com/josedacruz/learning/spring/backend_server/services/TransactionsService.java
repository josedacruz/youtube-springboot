package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.domain.Transaction;

import java.util.List;

public interface TransactionsService {
    List<Transaction> getAllTransactions();
}
