package com.ecommerce.orderservice.controller;


import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // Constructor injection is preferred over @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        // The service layer will handle Feign calls to Product/User services
        orderService.placeOrder(orderRequest);
        return new ResponseEntity<>("Order placed successfully!", HttpStatus.CREATED);
    }
}
