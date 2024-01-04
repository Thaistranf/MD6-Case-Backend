package com.example.dailyshop.service;

import com.example.dailyshop.model.account.Supplier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface SupplierService extends UserDetailsService {

    Iterable<Supplier> fillAll();
    Optional<Supplier> findById(Long id);
    void save (Supplier supplier);
    boolean checkLogin(Supplier supplier);
    boolean isRegister(Supplier supplier);
    boolean isCorrectConfirmPassword(Supplier supplier);
    UserDetails loadSupplierById(Long id);
    Supplier getCurrentSupplier();
    Supplier findBySupplierName(String supplierName);
}
