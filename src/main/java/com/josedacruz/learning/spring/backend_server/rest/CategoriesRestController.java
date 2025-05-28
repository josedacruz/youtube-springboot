package com.josedacruz.learning.spring.backend_server.rest;

import com.josedacruz.learning.spring.backend_server.domain.Category;
import com.josedacruz.learning.spring.backend_server.dtos.CategoryWithTransactions;
import com.josedacruz.learning.spring.backend_server.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesRestController {

    private final CategoryService categoryService;
    public CategoriesRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}/transactions")
    public CategoryWithTransactions getTransactionsByCategoryId(@PathVariable("id") int id) {
        return categoryService.getTransactionsByCategoryId(id);
    }

}
