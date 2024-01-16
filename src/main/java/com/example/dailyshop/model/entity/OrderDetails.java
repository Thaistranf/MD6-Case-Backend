package com.example.dailyshop.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "order_id")
    private Long orderId;
}
