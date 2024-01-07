package com.example.dailyshop.repository;


import com.example.dailyshop.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccount(String account);
    Iterable<Account> findByAccountContaining(String name);
}