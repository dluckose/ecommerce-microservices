package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.config.FeignClientInterceptor;
import com.ecommerce.productservice.dto.UserDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service",configuration = FeignClientInterceptor.class) // The application.name of the target service in Eureka
public interface UserClient {

    @GetMapping("/api/users")
    List<UserDetail> getAllUsers();
}
