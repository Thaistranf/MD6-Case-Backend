package com.example.dailyshop.service.webservice;

import java.util.List;
import java.util.Optional;

public interface IGenerateService<E> {
    List<E> findAll();
    E save(E e);
    void delete(Long productID);
    Optional<E> findById(Long productID);
}
