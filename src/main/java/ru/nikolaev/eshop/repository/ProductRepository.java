package ru.nikolaev.eshop.repository;

import org.springframework.stereotype.Repository;
import ru.nikolaev.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}