package com.example.dailyshop.model.entity;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.account.Supplier;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    @Column(nullable = false,unique = true)
    private String productName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String price;
    @Column(nullable = false)
    private String stockQuantity;
    @ManyToOne
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private List<Photo> photo;
    @ManyToOne
    private Account account;
}
