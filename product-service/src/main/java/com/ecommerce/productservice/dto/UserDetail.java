package com.ecommerce.productservice.dto;

import java.time.LocalDateTime;

public record UserDetail(Long id,
                         String name,
                         String phone,
                         String email,
                         String password,
                         String role,
                         LocalDateTime createdAt,
                         boolean deleted) {
}
