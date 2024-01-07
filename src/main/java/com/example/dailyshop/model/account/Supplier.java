package com.example.dailyshop.model.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@Table(name = "supplierTable")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String supplierName;
    private String contactName;
    @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)", message = "Invalid phone number")
    private String phone;

    private LocalDateTime editSupplierTime;
    private String imageSupplier;

    @ManyToOne
    private Account account;

    public Supplier(String supplierName, String contactName, String phone, LocalDateTime editSupplierTime, String imageSupplier, Account account) {
        this.supplierName = supplierName;
        this.contactName = contactName;
        this.phone = phone;
        this.editSupplierTime = editSupplierTime;
        this.imageSupplier = imageSupplier;
        this.account = account;
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getEditSupplierTime() {
        return editSupplierTime;
    }

    public void setEditSupplierTime(LocalDateTime editSupplierTime) {
        this.editSupplierTime = editSupplierTime;
    }

    public String getImageSupplier() {
        return imageSupplier;
    }

    public void setImageSupplier(String imageSupplier) {
        this.imageSupplier = imageSupplier;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
