package com.example.dailyshop.service.webservice.Order;

import com.example.dailyshop.model.entity.OrderDetails;
import com.example.dailyshop.model.revenue.Revenue;
import com.example.dailyshop.service.webservice.IGenerateService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface IOrderDetailService extends IGenerateService<OrderDetails> {
    List<OrderDetails> findOrderDetailsByOrderId(Long orderId);
    @Query(value = "SELECT MONTH(o.orderDate) as MONTHS, SUM(ord.quantity*ord.price)*0.7 as TotalRevenue FROM OrderDetails ord JOIN Order o ON ord.orderId = o.orderId JOIN Product p ON ord.product.productID= p.productID WHERE o.orderStatus = 'Paid'AND p.account.id=:accountId GROUP BY MONTHS, p.account.id")
    List<Map<Integer,Revenue>> getRevenueByMonth(@Param("accountId") Long accountId);
}
