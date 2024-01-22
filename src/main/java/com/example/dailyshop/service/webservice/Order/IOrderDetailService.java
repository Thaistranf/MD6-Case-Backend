package com.example.dailyshop.service.webservice.Order;

import com.example.dailyshop.model.entity.OrderDetails;
import com.example.dailyshop.service.webservice.IGenerateService;

import java.util.List;

public interface IOrderDetailService extends IGenerateService<OrderDetails> {
    List<OrderDetails> findOrderDetailsByOrderId(Long orderId);
}
