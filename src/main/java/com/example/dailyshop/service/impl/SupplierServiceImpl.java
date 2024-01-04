package com.example.dailyshop.service.impl;

import com.example.dailyshop.model.account.Supplier;
import com.example.dailyshop.model.account.SupplierPrinciple;
import com.example.dailyshop.repository.SupplierRepository;
import com.example.dailyshop.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public Iterable<Supplier> fillAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public boolean checkLogin(Supplier supplier) {
        Iterable<Supplier> suppliers = this.fillAll();
        boolean isCorrectSupplier = false;
        for (Supplier currentSupplier :suppliers) {
            if (currentSupplier.getSupplierName().equals(supplier.getSupplierName()) && supplier.getPassword().equals(currentSupplier.getPassword()) && currentSupplier.isEnabled()) {
                isCorrectSupplier = true;
                break;
            }
        }
        return isCorrectSupplier;
    }

    @Override
    public boolean isRegister(Supplier supplier) {
        boolean isRegister = false;
        Iterable<Supplier> suppliers = this.fillAll();
        for (Supplier currentSupplier : suppliers) {
            if (supplier.getSupplierName().equals(currentSupplier.getSupplierName())) {
                isRegister = true;
                break;
            }
        }
        return isRegister;
    }

    @Override
    public boolean isCorrectConfirmPassword(Supplier supplier) {
        return supplier.getPassword().equals(supplier.getConfirmPassword());
    }

    @Override
    public UserDetails loadSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isEmpty()) {
            throw new NullPointerException();
        }
        return SupplierPrinciple.build(supplier.get());
    }

    @Override
    public Supplier getCurrentSupplier() {
        Supplier supplier;
        String supplierName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            supplierName = ((UserDetails) principal).getUsername();
        } else {
            supplierName = principal.toString();
        }
        supplier = this.findBySupplierName(supplierName);
        return supplier;
    }

    @Override
    public Supplier findBySupplierName(String supplierName) {
        return supplierRepository.findBySupplierName(supplierName);
    }

    @Override
    public Iterable<Supplier> searchSupplierByName(String name) {
        return supplierRepository.findBySupplierNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String supplierName) {
        Supplier supplier = supplierRepository.findBySupplierName(supplierName);
        if (supplier == null) {
            throw new UsernameNotFoundException(supplierName);
        }
        if (this.checkLogin(supplier)) {
            return SupplierPrinciple.build(supplier);
        }
        boolean enable = false;
        boolean accountNonExpired = false;
        boolean credentialsNonExpired = false;
        boolean accountNonLocked = false;
        return new org.springframework.security.core.userdetails.User(supplier.getSupplierName(),
                supplier.getPassword(), enable, accountNonExpired, credentialsNonExpired,
                accountNonLocked, null);
    }

}
