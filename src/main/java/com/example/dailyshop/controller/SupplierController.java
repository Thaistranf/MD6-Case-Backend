package com.example.dailyshop.controller;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.account.Supplier;
import com.example.dailyshop.service.AccountService;
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

    @Autowired
    private AccountService accountService;

    @PutMapping("/suppliers/edit/{idAccount}")
    public ResponseEntity<Supplier> edit(@RequestBody Supplier supplier, @PathVariable Long idAccount) {
        Optional<Supplier> supplierOptional = supplierService.findByAccountId(idAccount);
        Optional<Supplier> supplierOptional1 = supplierService.findById(supplierOptional.get().getId());
        if(supplierOptional1.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplier.setId(supplierOptional1.get().getId());
        supplier.setStartDate(supplierOptional1.get().getStartDate());

        if (supplier.getImageSupplier() == null){
            //Neu ko thay doi thi se lay avatar cu
            supplier.setImageSupplier(supplierOptional1.get().getImageSupplier());
        } else {
            //Neu thay doi se lay avatar moi
            supplier.setImageSupplier(supplier.getImageSupplier());
        }

        //Muc dich: Check login lan dau chua co thong tin supplier -> checkProfile = false
        //se chuyen den trang de them thong tin supplier (edit supplier)
        //sau khi edit lan dau de co thong tin supplier -> checkProfile = true
        //se chuyen thang den trang chu cua supplier
        Optional<Account> accountOptional = accountService.findById(idAccount);
        accountOptional.get().setCheckProfile(true);

        supplier.setEditSupplierTime(LocalDateTime.now());
        supplier.setAccount(supplierOptional.get().getAccount());
        return new ResponseEntity<>(supplierService.save(supplier), HttpStatus.OK);
    }

    @GetMapping("/suppliers/findByAccountId/{id}")
    public ResponseEntity<Supplier> findByAccountId(@PathVariable Long id) {
        Optional<Supplier> supplierOptional = supplierService.findByAccountId(id);
        if(supplierOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplierOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/suppliers/current")
    public ResponseEntity<Supplier> currentSupplier() {
        Account currentAccount= accountService.getCurrentAccount();
        Optional<Supplier> supplierOptional = supplierService.findByAccountId(currentAccount.getId());
        if(supplierOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplierOptional.get(), HttpStatus.OK);
    }
}
