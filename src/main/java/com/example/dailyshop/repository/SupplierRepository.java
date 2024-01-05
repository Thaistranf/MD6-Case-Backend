package com.example.dailyshop.repository;

import com.example.dailyshop.model.account.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findBySupplierName(String supplierName);
    Iterable<Supplier> findBySupplierNameContainingIgnoreCase(String keyword);
}
