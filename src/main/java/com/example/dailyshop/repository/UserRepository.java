package com.example.dailyshop.repository;


import com.example.dailyshop.model.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Iterable<User> findByUsernameContaining(String name);
}