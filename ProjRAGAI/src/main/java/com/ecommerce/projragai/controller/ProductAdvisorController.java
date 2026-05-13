package com.ecommerce.projragai.controller;

import com.ecommerce.projragai.service.ProductDescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductAdvisorController {

    private final ProductDescriptionService productDescriptionService;

    public ProductAdvisorController(ProductDescriptionService productDescriptionService) {
        this.productDescriptionService = productDescriptionService;
    }

    /**
     * Endpoint to ask Gemini questions about products.
     * Example: GET /api/products/ask?query=I need a laptop for video editing under $1000
     */
    @GetMapping("/ask")
    public ResponseEntity<String> askGemini(@RequestParam(value = "query") String query) {
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Query cannot be empty");
        }

        try {
            String aiResponse = productDescriptionService.generateDescription(query);
            return ResponseEntity.ok(aiResponse);
        } catch (Exception e) {
            // Log the error (e.g., Gemini API quota exceeded or Embedding model timeout)
            return ResponseEntity.internalServerError()
                    .body("Error processing your request: " + e.getMessage());
        }
    }
}