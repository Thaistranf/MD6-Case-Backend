package com.example.dailyshop.model.entity;

import com.example.dailyshop.model.account.Account;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int stockQuantity;
    @Column(nullable = false)
    private boolean isDeleted = false;
    private LocalDateTime createAt;
    @ManyToOne
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private List<Photo> photo;
    @ManyToOne
    private Account account;
}
