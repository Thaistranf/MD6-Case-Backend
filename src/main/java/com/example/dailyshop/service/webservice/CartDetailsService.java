package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.CartDetails;
import com.example.dailyshop.repository.data.CartDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartDetailsService implements ICartDetailsService {
    @Autowired
    private CartDetailsRepository cartDetailsRepository;
    @Override
    public List<CartDetails> findAll() {
        return cartDetailsRepository.findAll();
    }

    @Override
    public CartDetails save(CartDetails cartDetails) {
        return cartDetailsRepository.save(cartDetails);
    }

    @Override
    public void delete(Long id) {
        cartDetailsRepository.deleteById(id);
    }

    @Override
    public Optional<CartDetails> findById(Long id) {
        return cartDetailsRepository.findById(id);
    }

    @Override
    public List<CartDetails> findOrderDetailsByCartId(Long cartId) {
        return cartDetailsRepository.findByOrderId(cartId);
    }

    @Override
    public void deleteById(Long id) {
        cartDetailsRepository.deleteById(id);
    }


}
