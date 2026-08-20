package com.jewellery.backend.controller;

import com.jewellery.backend.entity.Product;
import com.jewellery.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 1. GET ALL + FILTER BY CATEGORY
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            return productService.getProductsByCategory(category); // <-- NEW
        }
        return productService.getAllProducts();
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. POST - CREATE
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // 4. PUT - UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(productService.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}