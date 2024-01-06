package com.example.dailyshop.controller;

import com.example.dailyshop.model.account.Supplier;
import com.example.dailyshop.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PutMapping("/suppliers/edit/{idAccount}")
    public ResponseEntity<Supplier> edit(@RequestBody Supplier supplier, @PathVariable Long idAccount) {
        Optional<Supplier> supplierOptional = supplierService.findByAccountId(idAccount);
        Optional<Supplier> supplierOptional1 = supplierService.findById(supplierOptional.get().getId());
        if(supplierOptional1.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplier.setId(supplierOptional1.get().getId());
        supplier.setEditSupplierTime(LocalDateTime.now());
        supplier.setAccount(supplierOptional.get().getAccount());
        return new ResponseEntity<>(supplierService.save(supplier), HttpStatus.OK);
    }
}
