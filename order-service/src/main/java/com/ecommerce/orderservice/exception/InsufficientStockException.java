package com.ecommerce.orderservice.exception;

public class InsufficientStockException extends RuntimeException {
    private final Long productId;
    private final Integer requestedQuantity;
    private final Integer availableQuantity;

    public InsufficientStockException(Long productId, Integer requested, Integer available) {
        super(String.format("Product %d has insufficient stock. Requested: %d, Available: %d",
                productId, requested, available));
        this.productId = productId;
        this.requestedQuantity = requested;
        this.availableQuantity = available;
    }

    // Getters for the fields (so the ControllerAdvice can use them)
    public Long getProductId() { return productId; }
    public Integer getRequestedQuantity() { return requestedQuantity; }
    public Integer getAvailableQuantity() { return availableQuantity; }
}
