package com.example.dailyshop.model.account;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "supplierTable")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String supplierName;

    private LocalDate registrationDate;
    private String imageSupplier;

    @ManyToOne
    private User user;

    public Supplier(String supplierName, LocalDate registrationDate, String imageSupplier, User user) {
        this.supplierName = supplierName;
        this.registrationDate = registrationDate;
        this.imageSupplier = imageSupplier;
        this.user = user;
    }

    public Supplier() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }


    public String getImageSupplier() {
        return imageSupplier;
    }

    public void setImageSupplier(String imageSupplier) {
        this.imageSupplier = imageSupplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
