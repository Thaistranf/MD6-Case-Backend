package com.example.dailyshop.repository;

import com.example.dailyshop.model.account.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Iterable<Customer> findByCustomerNameContainingIgnoreCase(String keyword);
}
