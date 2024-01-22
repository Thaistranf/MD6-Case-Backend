package com.example.dailyshop.model.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "orderDetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "order_id")
    private Long orderId;
}
