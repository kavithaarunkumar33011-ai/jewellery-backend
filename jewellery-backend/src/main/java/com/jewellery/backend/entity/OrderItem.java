package com.jewellery.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity 
@Data
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "ORDER_ITEM_SEQ", allocationSize = 50)
    private Long id;

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}