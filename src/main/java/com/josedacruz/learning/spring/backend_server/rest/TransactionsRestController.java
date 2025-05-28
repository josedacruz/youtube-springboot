package com.josedacruz.learning.spring.backend_server.rest;

import com.josedacruz.learning.spring.backend_server.domain.Transaction;
import com.josedacruz.learning.spring.backend_server.services.TransactionsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionsRestController {

    private final TransactionsService transactionsService;
    public TransactionsRestController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }
}
