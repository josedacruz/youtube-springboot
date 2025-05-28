package com.josedacruz.learning.spring.backend_server.dtos;

import com.josedacruz.learning.spring.backend_server.domain.Category;
import com.josedacruz.learning.spring.backend_server.domain.Transaction;

import java.util.List;

public class CategoryWithTransactions {
    private Category category;
    private List<Transaction> transactions;

    public CategoryWithTransactions(Category category, List<Transaction> transactions) {
        this.category = category;
        this.transactions = transactions;
    }

    public Category getCategory() {
        return category;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
