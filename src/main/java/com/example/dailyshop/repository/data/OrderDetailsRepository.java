package com.example.dailyshop.repository.data;

import com.example.dailyshop.model.entity.OrderDetails;
import com.example.dailyshop.model.revenue.Revenue;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {

    List<OrderDetails> findOrderDetailsByOrderId(Long orderId);

    @Query(value = "SELECT MONTH(o.orderDate) as MONTHS, SUM(ord.quantity*ord.price)*0.7 as TotalRevenue FROM OrderDetails ord JOIN Order o ON ord.orderId = o.orderId JOIN Product p ON ord.product.productID= p.productID WHERE o.orderStatus = 'Paid'AND p.account.id=:accountId GROUP BY MONTHS, p.account.id")
    List<Map<Integer,Revenue>> getRevenueByMonth(@Param("accountId") Long accountId);
}
