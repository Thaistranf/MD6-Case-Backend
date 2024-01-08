package com.example.dailyshop.controller;

import com.example.dailyshop.model.account.*;
import com.example.dailyshop.service.AccountService;
import com.example.dailyshop.service.CustomerService;
import com.example.dailyshop.service.RoleService;
import com.example.dailyshop.service.SupplierService;
import com.example.dailyshop.service.impl.JwtService;
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
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/users")
    public ResponseEntity<Iterable<Account>> showAllUser() {
        Iterable<Account> users = accountService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<Iterable<Account>> showAllUserByAdmin() {
        Iterable<Account> users = accountService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/suppliers/register")
    public ResponseEntity<Object> createSupplier(@RequestBody Account account, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<Account> accounts = accountService.findAll();
        for (Account currentSupplier : accounts) {
            if (currentSupplier.getAccount().equals(account.getAccount())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!accountService.isCorrectConfirmPassword(account)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Role role = roleService.findByName("ROLE_SUPPLIER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);

        account.setRegistrationTime(LocalDateTime.now());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setConfirmPassword(passwordEncoder.encode(account.getConfirmPassword()));

        Account accountSupplier = accountService.save(account);

        Supplier supplier = new Supplier();
        supplier.setStartDate(LocalDate.now());
        supplier.setImageSupplier("https://png.pngtree.com/element_our/20200610/ourmid/pngtree-character-default-avatar-image_2237203.jpg");
        supplier.setAccount(accountSupplier);
        Supplier supplier1 = supplierService.save(supplier);
        return new ResponseEntity<>(supplier1, HttpStatus.CREATED);
    }

    @PostMapping("/customer/register")
    public ResponseEntity<Object> createCustomer(@RequestBody Account account, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<Account> accounts = accountService.findAll();
        for (Account currentAccount : accounts) {
            if (currentAccount.getAccount().equals(account.getAccount())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!accountService.isCorrectConfirmPassword(account)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Role role = roleService.findByName("ROLE_CUSTOMER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);

        account.setRegistrationTime(LocalDateTime.now());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setConfirmPassword(passwordEncoder.encode(account.getConfirmPassword()));

        Account accountCustomer = accountService.save(account);

        Customer customer = new Customer();
        customer.setImageCustomer("https://png.pngtree.com/element_our/20200610/ourmid/pngtree-character-default-avatar-image_2237203.jpg");
        customer.setAccount(accountCustomer);
        Customer customer1 = customerService.save(customer);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody Account account, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Iterable<Account> accounts = accountService.findAll();
        for (Account currentAccount : accounts) {
            if (currentAccount.getAccount().equals(account.getAccount())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!accountService.isCorrectConfirmPassword(account)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role role = roleService.findByName("ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);


        account.setRegistrationTime(LocalDateTime.now());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setConfirmPassword(passwordEncoder.encode(account.getConfirmPassword()));
        accountService.save(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getAccount(), account.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account currentAccount = accountService.findByAccount(account.getAccount());
        return ResponseEntity.ok(new JwtResponse(jwt, currentAccount.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Account> getProfile(@PathVariable Long id) {
        Optional<Account> accountOptional = this.accountService.findById(id);
        return accountOptional.map(account -> new ResponseEntity<>(account, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Account> updateUserProfile(@PathVariable Long id, @RequestBody Account account) {
        account.setId(id);
        Optional<Account> accountOptional = this.accountService.findById(id);
        Account account1 = accountOptional.get();
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        account1.setAccount(account.getAccount());
        account1.setEmail(account.getEmail());
        account1.setPassword(account1.getPassword());
        account1.setConfirmPassword(account1.getConfirmPassword());
        account1.setRoles(account1.getRoles());
        accountService.save(account1);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

//    @PutMapping("/users/avatar/{id}")
//    public ResponseEntity<Account> updateUserAvatar(@PathVariable Long id, @RequestBody Account account) {
//        account.setId(id);
//        Optional<Account> accountOptional = this.accountService.findById(id);
//        Account account1 = accountOptional.get();
//        if (!accountOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        account1.setAccount(account1.getAccount());
//        account1.setEmail(account1.getEmail());
//        account1.setPassword(account1.getPassword());
//        account1.setConfirmPassword(account1.getConfirmPassword());
//        account1.setRoles(account1.getRoles());
//        accountService.save(account1);
//        return new ResponseEntity<>(account, HttpStatus.OK);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Account>> searchUser(@RequestParam String name) {
        List<Account> accountList = (List<Account>) accountService.searchAccountByName(name);
        if (accountList == null) {
            List<Account> accounts = (List<Account>) accountService.findAll();
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(accountList, HttpStatus.OK);
        }
    }
}
