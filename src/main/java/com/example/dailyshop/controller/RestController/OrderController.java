package com.example.dailyshop.controller.RestController;

import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.model.revenue.Revenue;
import com.example.dailyshop.service.webservice.Order.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private BillService billService;

    @GetMapping("/getOrderCustomer")
    //tao hoa don cho khach hang
    public ResponseEntity<?> getOrder() {
        List<Order> ord = billService.createBillCustomer();
        return new ResponseEntity<>(ord, HttpStatus.OK);
    }

    @GetMapping(" ")
    // lay ra hoa don theo tai khoan
    public ResponseEntity<?> getOrderCustomer() {
        List<Order> orders = billService.getOrder();
        if (orders.isEmpty()) {
            return new ResponseEntity<>("Not Order By Account", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    @GetMapping("/suppliers/orderSupplier")
    // lay ra hoa don theo nha cung cap
    public ResponseEntity<?> getOrderSupplier() {
        List<Order> orders = billService.getOrderBySupplier();
        if (orders.isEmpty()) {
            return new ResponseEntity<>("Null value Order", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    @PutMapping("/suppliers/updateOrder/{id}")
    // sua trang thai don hang theo ID don hang
    public ResponseEntity<?> orderUpdate(@PathVariable Long id, @RequestBody Order order) {
        Order orders = billService.updateOrderStatus(id, order.getOrderStatus());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/suppliers/totalRevenue")
    // doanh thu cua nha cung cap theo tung thang
    public ResponseEntity<?> totalRevenue() {
        List<Map<Integer, Revenue>> total = billService.getRevenueByMonth();
        if (total.isEmpty()) {
            return new ResponseEntity<>("No Value", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(total, HttpStatus.OK);
        }
    }
}
