package com.jewellery.backend.repository;

import com.jewellery.backend.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
	 List<Product> findByCategoryIgnoreCase(String category);
    List<Product> findByNameContainingIgnoreCase(String name);
    
}
