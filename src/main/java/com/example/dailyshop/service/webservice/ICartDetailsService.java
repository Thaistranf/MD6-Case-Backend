package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.CartDetails;

import java.util.List;

public interface ICartDetailsService extends IGenerateService<CartDetails>{
    List<CartDetails> findOrderDetailsByCartId(Long cartId);

    void deleteById(Long id);
}
