package com.ecommerce.productservice.entity;

import jakarta.persistence.*; // The primary import for JPA
import lombok.Data;           // Recommended to reduce boilerplate
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // This links to the 'category' field in the Product entity
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
