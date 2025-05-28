package com.josedacruz.learning.spring.backend_server.repositories;

import com.josedacruz.learning.spring.backend_server.domain.Category;
import com.josedacruz.learning.spring.backend_server.dtos.CategoryWithTransactions;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    CategoryWithTransactions findTransactionsByCategoryId(int id);
}
