package com.example.dailyshop.model.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "customerTable")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private LocalDate dateOfBirth;

    private String address;

    @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)", message = "Invalid phone number")
    private String phone;

    private String imageCustomer;

    private LocalDateTime editCustomerTime;

    @ManyToOne
    private Account account;

    public Customer(Long id, String customerName, LocalDate dateOfBirth, String address, String phone, String imageCustomer, LocalDateTime editCustomerTime, Account account) {
        this.id = id;
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.imageCustomer = imageCustomer;
        this.editCustomerTime = editCustomerTime;
        this.account = account;
    }

    public Customer() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageCustomer() {
        return imageCustomer;
    }

    public void setImageCustomer(String imageCustomer) {
        this.imageCustomer = imageCustomer;
    }

    public LocalDateTime getEditCustomerTime() {
        return editCustomerTime;
    }

    public void setEditCustomerTime(LocalDateTime editCustomerTime) {
        this.editCustomerTime = editCustomerTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
