package com.example.dailyshop.service;

import com.example.dailyshop.model.account.Supplier;

import java.util.Optional;

public interface SupplierService {

    Iterable<Supplier> fillAll();
    Optional<Supplier> findById(Long id);
    Supplier save (Supplier supplier);
    Iterable<Supplier> searchSupplierByName(String name);

    Optional<Supplier> findByAccountId(Long id);
}
