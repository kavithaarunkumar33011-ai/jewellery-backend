package com.jewellery.backend.service; 

import com.jewellery.backend.entity.Product;
import com.jewellery.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class DataImportService {
    @Autowired private ProductRepository productRepo;
    @Autowired private RestTemplate restTemplate;

    @Transactional
    public String importProducts() {
        try {
            String url = "https://fakestoreapi.com/products";
            System.out.println("CALLING API...");
            
            // Use String first to see raw JSON
            String json = restTemplate.getForObject(url, String.class);
            System.out.println("RAW JSON: " + json.substring(0, 200)); // first 200 chars

            // Now convert to DTO
            ProductDTO[] products = restTemplate.getForObject(url, ProductDTO[].class);
            System.out.println("TOTAL PRODUCTS: " + products.length);
            System.out.println("FIRST PRODUCT TITLE: " + products[0].getTitle());
            
            for(ProductDTO dto : products){
                System.out.println("Saving: " + dto.getTitle() + " - " + dto.getPrice());
                Product p = new Product();
                p.setName(dto.getTitle());
                p.setPrice(dto.getPrice());
                p.setDescription(dto.getDescription());
                p.setCategory(dto.getCategory());
                p.setImageUrl(dto.getImage());
                p.setStock(10);
                p.setCodAvailable(true);
                productRepo.save(p);
            }
            return products.length + " Products Imported Successfully";
        } catch (Exception e) {
            e.printStackTrace(); 
            return "Error: " + e.getMessage();
        }
    }

    static class ProductDTO {
        private Long id; private String title; private Double price;
        private String description; private String category; private String image;
        public Long getId() { return id; } public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; } public void setTitle(String title) { this.title = title; }
        public Double getPrice() { return price; } public void setPrice(Double price) { this.price = price; }
        public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
        public String getCategory() { return category; } public void setCategory(String category) { this.category = category; }
        public String getImage() { return image; } public void setImage(String image) { this.image = image; }
    }
}