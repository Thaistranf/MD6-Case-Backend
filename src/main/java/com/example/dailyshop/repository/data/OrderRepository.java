package com.example.dailyshop.repository.data;


import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findOrderByAccountIdAndOrderStatus(Long account_id, OrderStatus orderStatus);

}
