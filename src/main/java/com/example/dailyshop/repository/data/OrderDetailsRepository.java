package com.example.dailyshop.repository.data;

import com.example.dailyshop.model.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {

    @Query("SELECT od FROM OrderDetails od WHERE od.order_id =:id")
    List<OrderDetails> findByOrderId(@Param("id") Long id);
//    void removeOrderDetailsByOrder_id(Long accountId, OrderDetails orderDetails);
}
