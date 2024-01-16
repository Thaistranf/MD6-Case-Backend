package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.OrderDetails;

import java.util.List;

public interface IOrderDetailsService extends IGenerateService<OrderDetails>{
    List<OrderDetails> findOrderDetailsByOrderId(Long orderId);

    void deleteById(Long id);
}
