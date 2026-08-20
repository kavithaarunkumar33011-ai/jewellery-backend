package com.jewellery.backend.service;

import com.jewellery.backend.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();    
    List<Product> getProductsByCategory(String category); // GET all
    Optional<Product> getProductById(Long id);         // GET by ID
    Product saveProduct(Product product);              // POST
    Optional<Product> updateProduct(Long id, Product product); // PUT
    boolean deleteProduct(Long id);                    // DELETE
}

