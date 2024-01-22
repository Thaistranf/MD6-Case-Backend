package com.example.dailyshop.service.impl;

import com.example.dailyshop.model.account.Supplier;
import com.example.dailyshop.repository.SupplierRepository;
import com.example.dailyshop.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }


    @Override
    public Iterable<Supplier> searchSupplierByName(String name) {
        return supplierRepository.findBySupplierNameContainingIgnoreCase(name);
    }

    @Override
    public Iterable<Supplier> findByCartId(Long cartId) {
        return supplierRepository.findByCartId(cartId);
    }

    @Override
    public Optional<Supplier> findByAccountId(Long id) {
        return supplierRepository.findByAccount_Id(id);
    }


}
