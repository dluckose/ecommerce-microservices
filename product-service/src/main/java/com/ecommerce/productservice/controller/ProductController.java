package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.UserDetail;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.service.ImageUploadService;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageUploadService imageUploadService;

   // @Autowired
   // private ProductAiService productAiService;

    // 1. Create Product (Requires ROLE_USER or ROLE_ADMIN based on your previous setup)
    /*@PostMapping("/category/{categoryId}")
    public ResponseEntity<Product> createProduct(
            @PathVariable Long categoryId,
            @RequestBody Product product) {

        Product savedProductProduct savedProduct = productService.addProduct(categoryId, product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }*/

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<Product> createProduct(
            @PathVariable Long categoryId,
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("image") MultipartFile file) throws IOException {

        // 1. Upload to Cloudinary
        String imagePath = imageUploadService.uploadImage(file);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImageUrl(imagePath);
        //add later - for delete
        //String publicId = uploadResult.get("public_id").toString();
        //String desc = productAiService.generateSmartDescription()
        System.out.println("00000");
        Product savedProduct = productService.addProduct(categoryId, product);
        System.out.println("1111");
        List<UserDetail> list = productService.getAllUsers();
        System.out.println("22222" +list);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(productService.getAllProducts(page, size, sortBy, direction));
    }

    // 2. Get All Products
   /* @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }*/

    // 3. Get Single Product by ID
 /*   @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }*/

    // 4. Get Products by Category
   /* @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
    }*/

    // 5. Delete Product
    /*@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }*/
}
