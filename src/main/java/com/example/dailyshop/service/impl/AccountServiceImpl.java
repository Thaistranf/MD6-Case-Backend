package com.example.dailyshop.service.impl;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.account.AccountPrinciple;
import com.example.dailyshop.repository.AccountRepository;
import com.example.dailyshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String account) {
        Account account1 = accountRepository.findByAccount(account);
        if (account1 == null) {
            throw new UsernameNotFoundException(account);
        }
        if (this.checkLogin(account1)) {
            return AccountPrinciple.build(account1);
        }
        boolean enable = false;
        boolean accountNonExpired = false;
        boolean credentialsNonExpired = false;
        boolean accountNonLocked = false;
        return new org.springframework.security.core.userdetails.User(account1.getAccount(),
                account1.getPassword(), enable, accountNonExpired, credentialsNonExpired,
                accountNonLocked, null);
    }


    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findByAccount(String account) {
        return accountRepository.findByAccount(account);
    }

    @Override
    public Account getCurrentAccount() {
        Account account;
        String acc;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            acc = ((UserDetails) principal).getUsername();
        } else {
            acc = principal.toString();
        }
        account = this.findByAccount(acc);
        return account;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public UserDetails loadAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new NullPointerException();
        }
        return AccountPrinciple.build(account.get());
    }

    @Override
    public boolean checkLogin(Account account) {
        Iterable<Account> accounts = this.findAll();
        boolean isCorrectUser = false;
        for (Account currentAccount : accounts) {
            if (currentAccount.getAccount().equals(account.getAccount()) && account.getPassword().equals(currentAccount.getPassword()) && currentAccount.isEnabled()) {
                isCorrectUser = true;
                break;
            }
        }
        return isCorrectUser;
    }

    @Override
    public boolean isRegister(Account account) {
        boolean isRegister = false;
        Iterable<Account> accounts = this.findAll();
        for (Account currentAccount : accounts) {
            if (account.getAccount().equals(currentAccount.getAccount())) {
                isRegister = true;
                break;
            }
        }
        return isRegister;
    }

    @Override
    public boolean isCorrectConfirmPassword(Account account) {
        return account.getPassword().equals(account.getConfirmPassword());
    }

    @Override
    public Iterable<Account> searchAccountByName(String name) {
        return accountRepository.findByAccountContaining(name);
    }
}
