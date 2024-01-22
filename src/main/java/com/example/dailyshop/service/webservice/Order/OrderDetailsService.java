package com.example.dailyshop.service.webservice.Order;

import com.example.dailyshop.model.entity.OrderDetails;
import com.example.dailyshop.repository.data.OrderDetailsRepository;
import com.example.dailyshop.repository.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailsService implements IOrderDetailService{
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Override
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    public void delete(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    @Override
    public Optional<OrderDetails> findById(Long id) {
        return orderDetailsRepository.findById(id);
    }

    @Override
    public List<OrderDetails> findOrderDetailsByOrderId(Long orderId) {
        return orderDetailsRepository.findOrderDetailsByOrderId(orderId);
    }
}
