package com.jewellery.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity 
@Data
@Table(name = "cart_item")
public class CartItem {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "CART_SEQ", allocationSize = 1)
    private Long id; // <-- ID goes here

    @ManyToOne(fetch = FetchType.LAZY) // <-- Relationship goes here
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // Add this for product too
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    
    
}