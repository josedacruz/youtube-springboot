package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.domain.Category;
import com.josedacruz.learning.spring.backend_server.dtos.CategoryWithTransactions;
import com.josedacruz.learning.spring.backend_server.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryWithTransactions getTransactionsByCategoryId(int id) {
        return categoryRepository.findTransactionsByCategoryId(id);
    }
}
