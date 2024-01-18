package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.model.entity.Product;
import com.example.dailyshop.model.entity.Revenue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IOrderService extends IGenerateService<Order>{
    Optional<Order> findOrderByAccountIdAndOrderStatus(Long account_id, OrderStatus orderStatus);
    List<Order> findOrderByAccountAndOrderStatus(Account account_id, OrderStatus orderStatus);
//    @Query(value = "SELECT MONTH(o.orderDate) as Month, SUM((ord.price * ord.quantity)) * 0.7 as TotalMoney FROM OrderDetails ord JOIN Product p on ord.product.productID = p.productID JOIN Order o on ord.orderId = o.id WHERE o.orderStatus = 'Paid' AND YEAR(o.orderDate) =:year AND p.account.id = ? GROUP BY p.account.id, Month")
//    List<Revenue> getMonthlyRevenue(@Param("year") int year);
}
