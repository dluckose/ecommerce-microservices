package com.ecommerce.productservice.service;

import com.ecommerce.productservice.controller.UserClient;
import com.ecommerce.productservice.dto.UserDetail;
import com.ecommerce.productservice.entity.Category;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.CategoryRepository;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserClient userClient;
    //@Autowired
    //private ProductAiService productAiService;
    public List<UserDetail> getAllUsers() {
        return userClient.getAllUsers();
    }
    public Product addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        //String desc = productAiService.generateSmartDescription(product.getName(),category.getName());
        //product.setDescription(desc);
        product.setCategory(category);
        return productRepository.save(product);
    }
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    public Page<Product> getAllProducts(int page, int size, String sortBy, String direction) {
        // 1. Create a Sort object (ASC or DESC)
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        // 2. Create a Pageable object
        Pageable pageable = PageRequest.of(page, size, sort);

        // 3. Use the built-in repository method
        return productRepository.findAll(pageable);
    }
}
