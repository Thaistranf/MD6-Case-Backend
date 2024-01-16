package com.example.dailyshop.controller.RestController;
import com.example.dailyshop.model.entity.OrderDetails;
import com.example.dailyshop.service.webservice.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping("/account/getOrderDetails")
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails(){
        try {
            List<OrderDetails> orderDetails = orderDetailsService.findAll();
            if(orderDetails.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(orderDetails,HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println("1223"+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/account/findOrderDetailsByOrderId/{orderId}")
    ResponseEntity<List<OrderDetails>> findOrderDetails(@PathVariable Long orderId){
        List<OrderDetails> orderDetails = orderDetailsService.findOrderDetailsByOrderId(orderId);
        if(orderDetails!=null){
            return new ResponseEntity<>(orderDetails,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
