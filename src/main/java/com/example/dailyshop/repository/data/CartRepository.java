package com.example.dailyshop.repository.data;


import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Cart;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByAccountId(Long account_id);

    List<Cart> findOrderByAccount(Account account_id);

}
