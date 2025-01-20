package ru.nikolaev.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikolaev.eshop.model.Cart_item;

@Repository
public interface CartItemRepository  extends JpaRepository<Cart_item, Long> {

}
