package com.example.dailyshop.controller;

import com.example.dailyshop.model.account.Customer;
import com.example.dailyshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PutMapping("/customer/edit/{idAccount}")
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer, @PathVariable Long idAccount){
        Optional<Customer> customerOptional = customerService.findByAccountId(idAccount);
        Optional<Customer> customerOptional1 = customerService.findById(customerOptional.get().getId());
        if (customerOptional1.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional1.get().getId());
        customer.setEditCustomerTime(LocalDateTime.now());
        customer.setAccount(customerOptional.get().getAccount());
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
    }
}
