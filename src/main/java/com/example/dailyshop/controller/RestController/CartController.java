package com.example.dailyshop.controller.RestController;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Cart;
import com.example.dailyshop.model.entity.CartDetails;
import com.example.dailyshop.service.AccountService;
import com.example.dailyshop.service.webservice.CartDetailsService;
import com.example.dailyshop.service.webservice.CartService;
import com.example.dailyshop.service.webservice.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CartController {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private CartService orderService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/customer/addProductToOrders")
    //thêm sản phẩm vào giỏ hàng
    public ResponseEntity<?> addProductToOrders(@RequestBody CartDetails cartDetails) {
        try {
            Account currentAccount = accountService.getCurrentAccount();
            Cart cart = cartService.addProductToOrder(currentAccount.getId(), cartDetails);
            if (cart != null) {
                return new ResponseEntity<>(cart, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Can't Add Order" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/customer/getOrder")
        //xem tat ca order da mua
    ResponseEntity<List<Cart>> findAllOrder() {
        List<Cart> carts = orderService.findAll();
        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(carts, HttpStatus.OK);
        }
    }

    @GetMapping("/customer/cart")
    //Xem gio hang cua khach hang
    public ResponseEntity<Cart> getCart() {
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
    public ResponseEntity<?> getOrderCustomer() {
        ResponseEntity<?> orders = cartService.findOrderCustomerPaid();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/customer/countCartDetails")
    public ResponseEntity<Integer> countCartDetails(){
        int count = cartService.countDetails();
        if(count == 0 ){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(count,HttpStatus.OK);
        }
    }

    @PutMapping("/account/updateCartDetail/{cartDetailId}")
    public ResponseEntity<?> updateCartDetail(@PathVariable Long cartDetailId, @RequestParam int quantity){
        cartService.updateCartDetail(cartDetailId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}