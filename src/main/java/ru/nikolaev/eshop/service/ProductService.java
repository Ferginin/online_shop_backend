package ru.nikolaev.eshop.service;

import ru.nikolaev.eshop.model.Product;
import ru.nikolaev.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    // Получить все продукты
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Получить продукт по ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Создать новый продукт
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Обновить продукт
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDiscountPrice(updatedProduct.getDiscountPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        return productRepository.save(existingProduct);
    }

    // Удалить продукт
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}