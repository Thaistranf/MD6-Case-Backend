package com.example.dailyshop.model.entity;

import com.example.dailyshop.model.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int quantity;
    private BigDecimal totalAmount;
    @Column(nullable = false)
    private LocalDateTime orderDate;
//    @Enumerated(EnumType.STRING)
//    private OrderStatus orderStatus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private Set<CartDetails> cartDetails;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
