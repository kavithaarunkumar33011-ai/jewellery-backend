package com.jewellery.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    private Long id;
   
    private String name;  
    @Column (length = 4000)
    private String description;
    
    @Column (length = 4000) 
    private Double price;  
    
    @Column (name = "image_url")
    private String imageUrl;
    private Integer stock;
    
    private String category;       
    private String material;       
    private String plating;        
    private String occasion;       
    @Column(name = "cod_available")
    private Boolean codAvailable;
}