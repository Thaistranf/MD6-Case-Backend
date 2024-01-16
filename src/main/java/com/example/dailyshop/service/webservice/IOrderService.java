package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.model.entity.Product;

import java.util.Optional;

public interface IOrderService extends IGenerateService<Order>{
    Optional<Order> findOrderByAccountIdAndOrderStatus(Long account_id, OrderStatus orderStatus);
}
