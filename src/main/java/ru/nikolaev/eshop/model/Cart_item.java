package ru.nikolaev.eshop.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Cart_item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_item_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int quantity;
}
