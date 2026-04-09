package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.config.FeignClientInterceptor;
import com.ecommerce.orderservice.dto.ProductDTO;
import com.ecommerce.orderservice.dto.StockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service",configuration = FeignClientInterceptor.class)
public interface ProductClient {

    @GetMapping("api/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);

    @PostMapping ("api/products/{id}/reduce-stock")
    void reduceStock(@PathVariable("id") Long id, @RequestBody StockRequest stockRequest);
}
