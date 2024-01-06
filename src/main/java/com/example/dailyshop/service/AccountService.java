package com.example.dailyshop.service;

import com.example.dailyshop.model.account.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AccountService extends UserDetailsService {
    Account save(Account account);

    Iterable<Account> findAll();

    Account findByAccount(String account);

    Account getCurrentAccount();

    Optional<Account> findById(Long id);

    UserDetails loadAccountById(Long id);

    boolean checkLogin(Account account);

    boolean isRegister(Account account);

    boolean isCorrectConfirmPassword(Account account);

    Iterable<Account> searchAccountByName(String name);
}
