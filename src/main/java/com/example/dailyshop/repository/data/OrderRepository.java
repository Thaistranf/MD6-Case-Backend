package com.example.dailyshop.repository.data;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByAccountId(Long accountId, Sort sort);

    List<Order> findOrderBySupplierId(Long supplierId,Sort sort);

    Optional<Order> findOrderByAccountIdAndSupplierIdAndOrderStatus(Long account_id, Long supplier_id, OrderStatus orderStatus);
}
