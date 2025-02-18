package ru.nikolaev.eshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikolaev.eshop.model.Cart_item;
import ru.nikolaev.eshop.service.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    // Получить все элементы корзины
    @GetMapping
    public ResponseEntity<List<Cart_item>> getAllCartItems() {
        List<Cart_item> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    // Получить элемент корзины по ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart_item> getCartItemById(@PathVariable Long id) {
        Cart_item cartItem = cartItemService.getCartItemById(id);
        return ResponseEntity.ok(cartItem);
    }

    // Создать новый элемент корзины
    @PostMapping
    public ResponseEntity<Cart_item> createCartItem(@RequestBody Cart_item cartItem) {
        Cart_item createdCartItem = cartItemService.createCartItem(cartItem);
        return ResponseEntity.ok(createdCartItem);
    }

    // Обновить элемент корзины
    @PutMapping("/{id}")
    public ResponseEntity<Cart_item> updateCartItem(@PathVariable Long id, @RequestBody Cart_item updatedCartItem) {
        Cart_item cartItem = cartItemService.updateCartItem(id, updatedCartItem);
        return ResponseEntity.ok(cartItem);
    }

    // Удалить элемент корзины
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
