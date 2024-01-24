package com.example.dailyshop.service.webservice.Order;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.repository.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> findAll() {
        return null;
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
    public List<Order> findOrderByAccountId(Long accountId, Sort sort) {
        return orderRepository.findOrderByAccountId(accountId,sort);
    }

    @Override
    public List<Order> findOrderBySupplierId(Long supplierId,Sort sort) {
        return orderRepository.findOrderBySupplierId(supplierId,sort);
    }


    @Override
    public Optional<Order> findOrderByAccountIdAndSupplierIdAndOrderStatus(Long account_id, Long supplier_id, OrderStatus orderStatus) {
        return orderRepository.findOrderByAccountIdAndSupplierIdAndOrderStatus(account_id,supplier_id,orderStatus);
    }
}
