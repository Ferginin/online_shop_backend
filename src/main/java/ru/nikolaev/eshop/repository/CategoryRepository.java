package ru.nikolaev.eshop.repository;

import org.springframework.stereotype.Repository;
import ru.nikolaev.eshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}