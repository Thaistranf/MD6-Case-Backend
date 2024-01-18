package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Cart;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.repository.data.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Optional<Cart> findOrderByAccountId(Long account_id) {
        return cartRepository.findOrderByAccountId(account_id);
    }

    @Override
    public List<Cart> findOrderByAccount(Account account_id) {
        return cartRepository.findOrderByAccount(account_id);
    }


}
