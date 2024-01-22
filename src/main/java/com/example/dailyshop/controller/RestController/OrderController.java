package com.example.dailyshop.controller.RestController;

import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.service.webservice.Order.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private BillService billService;

    @GetMapping("/getOrderCustomer")
    //xac nhan thanh toan don hang
    public ResponseEntity<?> getOrder(){
        List<Order> ord = billService.createBillCustomer();
        return new ResponseEntity<>(ord, HttpStatus.OK);
    }

    @GetMapping("/account/order")
    public ResponseEntity<?> getOrderCustomer(){
        List<Order> orders = billService.getOrder();
        if(orders.isEmpty()){
            return new ResponseEntity<>("Not Order By Account",HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(orders,HttpStatus.OK);
        }
    }

    @GetMapping("/suppliers/orderSupplier")
    //Lay ra ds don hang khach da xac nhan mua
    public ResponseEntity<?> getOrderSupplier(){
        List<Order> orders = billService.getOrderBySupplier();
        if(orders.isEmpty()){
            return new ResponseEntity<>("Null value Order",HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(orders,HttpStatus.OK);
        }
    }

    @PutMapping("/suppliers/updateOrder/{id}")
    //chu shop xac nhan trang thai don hang cua khach xac nhan mua
    public ResponseEntity<?> orderUpdate(@PathVariable Long id,@RequestBody OrderStatus orderStatus){
        Order order = billService.updateOrderStatus(id,orderStatus);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}
