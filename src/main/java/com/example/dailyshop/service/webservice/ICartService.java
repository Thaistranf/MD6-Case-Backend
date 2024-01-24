package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Cart;
import com.example.dailyshop.model.entity.OrderStatus;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICartService extends IGenerateService<Cart>{
    Optional<Cart> findCartByAccountId(Long account_id);
    List<Cart> findOrderByAccount(Account account_id);

//    @Query(value = "SELECT MONTH(o.orderDate) as Month, SUM((ord.price * ord.quantity)) * 0.7 as TotalMoney FROM OrderDetails ord JOIN Product p on ord.product.productID = p.productID JOIN Order o on ord.orderId = o.id WHERE o.orderStatus = 'Paid' AND YEAR(o.orderDate) =:year AND p.account.id = ? GROUP BY p.account.id, Month")
//    List<Revenue> getMonthlyRevenue(@Param("year") int year);
}
