package ru.nikolaev.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikolaev.eshop.model.Cart_item;
import ru.nikolaev.eshop.model.Category;
import ru.nikolaev.eshop.repository.CartItemRepository;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public List<Cart_item> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public Cart_item getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart item not found"));
    }
}
