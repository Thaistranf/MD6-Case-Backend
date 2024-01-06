package com.example.dailyshop.repository;

import com.example.dailyshop.model.account.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findBySupplierName(String supplierName);
    Iterable<Supplier> findBySupplierNameContainingIgnoreCase(String keyword);
    @Query(value = "select * from supplier_table where account_id = :id", nativeQuery = true)
    Optional<Supplier> findByAccount_Id(@Param("id") Long id);
}
