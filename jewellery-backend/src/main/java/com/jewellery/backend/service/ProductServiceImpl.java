package com.jewellery.backend.service;

import com.jewellery.backend.entity.Product;
import com.jewellery.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        return productRepository.findById(id).map(existing -> {
            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());
            existing.setCategory(product.getCategory());
            existing.setMaterial(product.getMaterial());
            existing.setPlating(product.getPlating());
            existing.setStock(product.getStock());
            existing.setOccasion(product.getOccasion());
            existing.setImageUrl(product.getImageUrl());
            existing.setCodAvailable(product.getCodAvailable());
            return productRepository.save(existing);
        });
    }
    @Override
    public List<Product> getProductsByCategory(String category) { // <-- ADD THIS METHOD
        return productRepository.findByCategoryIgnoreCase(category);
    }
    @Override
    public boolean deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}