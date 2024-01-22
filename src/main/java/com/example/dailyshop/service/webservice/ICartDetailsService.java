package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.CartDetails;

import java.util.List;
import java.util.Optional;

public interface ICartDetailsService extends IGenerateService<CartDetails>{
    List<CartDetails> findCartDetailsByCartId(Long cartId);
    Optional<CartDetails> findCartDetailsByCartIdAndId(Long cartId, Long productId);

    void deleteById(Long id);

    Integer countCartDetailsByCartId(Long cartId);

}
