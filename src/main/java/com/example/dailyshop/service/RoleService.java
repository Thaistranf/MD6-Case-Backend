package com.example.dailyshop.service;


import com.example.dailyshop.model.account.Role;


public interface RoleService {
    Iterable<Role> findAll();

    void save(Role role);

    Role findByName(String name);
}
