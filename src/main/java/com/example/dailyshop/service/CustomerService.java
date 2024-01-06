package com.example.dailyshop.service;

import com.example.dailyshop.model.account.Customer;

import java.util.Optional;

public interface CustomerService {
    Iterable<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
    Iterable<Customer> searchCustomerByName(String name);
}
