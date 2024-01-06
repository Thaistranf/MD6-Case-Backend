package com.example.dailyshop.repository;

import com.example.dailyshop.model.account.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Iterable<Customer> findByCustomerNameContainingIgnoreCase(String keyword);
    @Query(value = "select * from customer_table where account_id = :id", nativeQuery = true)
    Optional<Customer> findByAccount_Id(@Param("id") Long id);
}
