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

    // Получить все элементы корзины
    public List<Cart_item> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    // Получить элемент корзины по ID
    public Cart_item getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart item not found"));
    }

    // Создать новый элемент корзины
    public Cart_item createCartItem(Cart_item cartItem) {
        return cartItemRepository.save(cartItem);
    }

    // Обновить элемент корзины
    public Cart_item updateCartItem(Long id, Cart_item updatedCartItem) {
        Cart_item existingCartItem = getCartItemById(id);
        existingCartItem.setProduct(updatedCartItem.getProduct());
        existingCartItem.setUser(updatedCartItem.getUser());
        existingCartItem.setQuantity(updatedCartItem.getQuantity());
        return cartItemRepository.save(existingCartItem);
    }

    // Удалить элемент корзины
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
