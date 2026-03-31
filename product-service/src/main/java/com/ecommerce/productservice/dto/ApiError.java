package com.ecommerce.productservice.dto;

public record ApiError(
        String message,
        int status,
        long timestamp
) {}