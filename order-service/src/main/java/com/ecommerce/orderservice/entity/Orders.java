package com.ecommerce.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private Double price;
    private Integer quantity;
    private String status;
}
