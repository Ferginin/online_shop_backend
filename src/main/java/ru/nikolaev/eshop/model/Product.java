package ru.nikolaev.eshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    private String name;
    private String description;
    private String imageUrl;
    private int stock;
    private BigDecimal price;
    private BigDecimal discountPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}