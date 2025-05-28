package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.domain.Transaction;
import com.josedacruz.learning.spring.backend_server.repositories.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsRepository transactionsRepository;
    public TransactionsServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionsRepository.getAllTransactions();
    }
}
