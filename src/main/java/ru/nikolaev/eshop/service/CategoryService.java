package ru.nikolaev.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikolaev.eshop.model.Category;
import ru.nikolaev.eshop.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
