package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.domain.Category;
import com.josedacruz.learning.spring.backend_server.dtos.CategoryWithTransactions;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    CategoryWithTransactions getTransactionsByCategoryId(int id);
    Category save(Category category);
}
