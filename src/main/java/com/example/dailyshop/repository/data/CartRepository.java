package com.example.dailyshop.repository.data;


import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Cart;
import com.example.dailyshop.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findOrderByAccountId(Long account_id);

    List<Cart> findOrderByAccount(Account account_id);
}
