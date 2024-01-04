package com.example.dailyshop.controller;

import com.example.dailyshop.model.account.JwtResponse;
import com.example.dailyshop.model.account.Role;
import com.example.dailyshop.model.account.Supplier;
import com.example.dailyshop.model.account.User;
import com.example.dailyshop.service.RoleService;
import com.example.dailyshop.service.SupplierService;
import com.example.dailyshop.service.impl.JwtService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class SupplierController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService2 jwtService2;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/suppliers")
    public ResponseEntity<Iterable<Supplier>> showAllSupplier() {
        Iterable<Supplier> suppliers = supplierService.fillAll();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @PostMapping("/suppliers/register")
    public ResponseEntity<Object> createSupplier(@RequestBody Supplier supplier, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Iterable<Supplier> suppliers = supplierService.fillAll();
        for (Supplier currentSupplier : suppliers) {
            if (currentSupplier.getSupplierName().equals(supplier.getSupplierName())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!supplierService.isCorrectConfirmPassword(supplier)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (supplier.getRoles() == null) {
            Role role = roleService.findByName("ROLE_SUPPLIER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            supplier.setRoles(roles);
        }

        supplier.setRegistrationDate(LocalDate.now());
        supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
        supplier.setConfirmPassword(passwordEncoder.encode(supplier.getConfirmPassword()));
        supplierService.save(supplier);
        return new ResponseEntity<>(supplier, HttpStatus.CREATED);
    }

    @PostMapping("/suppliers/login")
    public ResponseEntity<?> login(@RequestBody Supplier supplier) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(supplier.getSupplierName(), supplier.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService2.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Supplier currentSupplier = supplierService.findBySupplierName(supplier.getSupplierName());
        return ResponseEntity.ok(new JwtResponse(jwt, currentSupplier.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }
}
