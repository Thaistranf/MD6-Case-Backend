package com.example.dailyshop.controller.RestController;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderDetails;
import com.example.dailyshop.service.AccountService;
import com.example.dailyshop.service.webservice.OrderDetailsService;
import com.example.dailyshop.service.webservice.OrderService;
import com.example.dailyshop.service.webservice.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class OrdersController {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/customer/addProductToOrders")
    //thêm sản phẩm vào giỏ hàng
    public ResponseEntity<?> addProductToOrders(@RequestBody OrderDetails orderDetails) {
        try {
            Account currentAccount= accountService.getCurrentAccount();
            Order order = cartService.addProductToOrder(currentAccount.getId(), orderDetails);
            if (order != null) {
                return new ResponseEntity<>(order, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Can't Add Order" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/customer/getOrder")
        //xem tat ca order da mua
    ResponseEntity<List<Order>> findAllOrder() {
        List<Order> orders = orderService.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    @GetMapping("/customer/cart")
    //Xem gio hang cua khach hang
    public ResponseEntity<Order> getCart() {
        return cartService.getCart();
    }

    @GetMapping("/customer/payment")
    //thanh toan gio hang
    public ResponseEntity<?> paymentOrder() {
        try {
            return new ResponseEntity<>(cartService.paymentOrder(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/account/removeOrderDetails/{detailsId}")
    public ResponseEntity<?> removeOrderDetail(@PathVariable Long detailsId) {
        return cartService.removeOrderDetail(detailsId);
    }

    @GetMapping("/account/getOrderCustomerPaid")
    public ResponseEntity<?> getOrderCustomer(){
        ResponseEntity<?> orders = cartService.findOrderCustomerPaid();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}