package com.example.dailyshop.service.webservice.Order;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.service.webservice.IGenerateService;

import java.util.List;
import java.util.Optional;

public interface IOrderService extends IGenerateService<Order> {
    List<Order> findOrderByAccountId(Long accountId);

    List<Order> findOrderBySupplierId(Long supplierId);

    Optional<Order> findOrderByAccountIdAndSupplierIdAndOrderStatus(Long account_id, Long supplier_id, OrderStatus orderStatus);
}
