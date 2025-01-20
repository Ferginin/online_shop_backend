package ru.nikolaev.eshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikolaev.eshop.model.Cart_item;
import ru.nikolaev.eshop.service.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/cartitems")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<List<Cart_item>> getAllCategories() {
        List<Cart_item> Cart_items = cartItemService.getAllCartItems();
        return ResponseEntity.ok(Cart_items);
    }

    @GetMapping("/{id}")
    public Cart_item getCategoryById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id);
    }
}
