package com.example.dailyshop.model.entity;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.account.Supplier;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "oders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private int quantity;
    private BigDecimal totalMoney;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private OrderStatus orderStatus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private Set<OrderDetails> orderDetails;
    @Column(nullable = false)
    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
