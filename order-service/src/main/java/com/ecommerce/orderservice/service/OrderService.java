package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.controller.ProductClient;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.ProductDTO;
import com.ecommerce.orderservice.dto.StockRequest;
import com.ecommerce.orderservice.entity.Orders;
import com.ecommerce.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private ProductClient productClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public Orders placeOrder(OrderRequest request) {
        // 1. Call Product Service to verify details
        ProductDTO product = productClient.getProductById(request.productId());

       /* if (product.stock() < request.quantity()) {
            throw new InsufficientStockException("Not enough stock for: " + product.name());
        }*/

        // 2. Save the Order
        Orders orders = new Orders();
        orders.setUserId(request.userId());
        orders.setProductId(request.productId());
        orders.setQuantity(request.quantity());
        orders.setStatus("initial");
        //order.setPrice(product.);
        Orders savedOrder = orderRepository.save(orders);
        StockRequest stockReq = new StockRequest(request.quantity());

        // 3. Update stock in Product Service
        productClient.reduceStock(request.productId(),stockReq);

        return savedOrder;
    }
}
