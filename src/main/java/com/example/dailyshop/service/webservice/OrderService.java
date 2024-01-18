package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.model.entity.Revenue;
import com.example.dailyshop.repository.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> findOrderByAccountIdAndOrderStatus(Long account_id, OrderStatus orderStatus) {
        return orderRepository.findOrderByAccountIdAndOrderStatus(account_id, orderStatus);
    }

    @Override
    public List<Order> findOrderByAccountAndOrderStatus(Account account_id, OrderStatus orderStatus) {
        return orderRepository.findOrderByAccountAndOrderStatus(account_id,orderStatus);
    }


}
