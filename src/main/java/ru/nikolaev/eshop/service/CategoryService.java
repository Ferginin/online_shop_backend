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

    // Получить все категории
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Получить категорию по ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // Создать новую категорию
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Обновить категорию
    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setParent(updatedCategory.getParent());
        return categoryRepository.save(existingCategory);
    }

    // Удалить категорию
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
