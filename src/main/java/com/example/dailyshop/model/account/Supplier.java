package com.example.dailyshop.model.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
@Entity
@Table(name = "supplierTable")
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String supplierName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String confirmPassword;
    @Column(nullable = false, unique = true)
    @Email(message = "Email không hợp lệ")
    private String email;

    private LocalDate registrationDate;
    private String imageSupplier;
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "supplier_role",
            joinColumns = {@JoinColumn(name = "supplier_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    public Supplier(String supplierName, String password, String confirmPassword, String email, LocalDate registrationDate, String imageSupplier, boolean enabled, Set<Role> roles) {
        this.supplierName = supplierName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.registrationDate = registrationDate;
        this.imageSupplier = imageSupplier;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Supplier() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getImageSupplier() {
        return imageSupplier;
    }

    public void setImageSupplier(String imageSupplier) {
        this.imageSupplier = imageSupplier;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
