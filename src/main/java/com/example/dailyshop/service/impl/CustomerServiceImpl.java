package com.example.dailyshop.service.impl;

import com.example.dailyshop.model.account.Customer;
import com.example.dailyshop.repository.CustomerRepository;
import com.example.dailyshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Iterable<Customer> searchCustomerByName(String name) {
        return customerRepository.findByCustomerNameContainingIgnoreCase(name);
    }
}
