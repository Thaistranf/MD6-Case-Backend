package com.example.dailyshop.model.account;

import com.example.dailyshop.model.address.District;
import com.example.dailyshop.model.address.Province;
import com.example.dailyshop.model.address.Ward;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
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

    private String address;
    @ManyToOne
    private Province province;
    @ManyToOne
    private District district;
    @ManyToOne
    private Ward ward;

    private LocalDate startDate;

    private LocalDateTime editSupplierTime;
    private String imageSupplier;

    @ManyToOne
    private Account account;

    public Supplier(Long id, String supplierName, String contactName, String phone, String address, Province province, District district, Ward ward, LocalDate startDate, LocalDateTime editSupplierTime, String imageSupplier, Account account) {
        this.id = id;
        this.supplierName = supplierName;
        this.contactName = contactName;
        this.phone = phone;
        this.address = address;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.startDate = startDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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
