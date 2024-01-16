package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderDetails;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.model.entity.Product;
import com.example.dailyshop.repository.AccountRepository;
import com.example.dailyshop.repository.data.OrderDetailsRepository;
import com.example.dailyshop.repository.data.OrderRepository;
import com.example.dailyshop.repository.data.ProductRepository;
import com.example.dailyshop.service.AccountService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.dailyshop.model.entity.OrderStatus.Paid;
import static com.example.dailyshop.model.entity.OrderStatus.Unpaid;


@Service
public class ShoppingCartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderDetailsService orderDetailsService;

    public Order addProductToOrder(Long account_id, OrderDetails orderDetail) {
        //thêm sản phẩm vào giỏ hàng.
        Optional<Order> order = orderRepository.findOrderByAccountIdAndOrderStatus(account_id, Unpaid);
        Optional<Product> product = productRepository.findById(orderDetail.getProduct().getProductID());
        Order orderNew;
        if (order.isPresent()) {
            orderNew = order.get();
        } else {
            orderNew = new Order();
            orderNew.setOrderDate(LocalDateTime.now());
            orderNew.setOrderStatus(Unpaid);
            orderNew.setOrderDetails(new HashSet<>());
            Account account = accountRepository.findById(account_id).get();
            orderNew.setAccount(account);
        }
        boolean flag = false;
        for (OrderDetails item : orderNew.getOrderDetails()) {
            if (Objects.equals(item.getProduct().getProductID(), orderDetail.getProduct().getProductID())) {
                item.setQuantity(item.getQuantity() + orderDetail.getQuantity());
                flag = true;
                break;
            }
        }
        if (!flag) {
            orderDetail.setProduct(product.get());
            orderNew.getOrderDetails().add(orderDetail);
        }
        BigDecimal totalAmount = getTotalAmount(orderNew);
        orderNew.setTotalAmount(totalAmount);
        int countOrderDetails = getQuantityOrder(orderNew);
        orderNew.setQuantity(countOrderDetails);
        return orderRepository.save(orderNew);


    }

    @NotNull
    private static BigDecimal getTotalAmount(Order orderNew) {
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        for (OrderDetails item : orderNew.getOrderDetails()) {
            item.setPrice(item.getProduct().getPrice());
            totalAmount = totalAmount.add(BigDecimal.valueOf((long) item.getQuantity() * item.getPrice()));
        }
        return totalAmount;
    }

    public Integer getQuantityOrder(Order order) {
        int totalOrderDetails = 0;
        for (OrderDetails count : order.getOrderDetails()) {
//            count.setQuantity(count.getQuantity());
            totalOrderDetails = totalOrderDetails + count.getQuantity();
        }
        return totalOrderDetails;
    }

    public ResponseEntity<Order> getCart() {
        //xem giỏ hàng của khách hàng.
        Account currentAccount = accountService.getCurrentAccount();
        Optional<Order> order = orderRepository.findOrderByAccountIdAndOrderStatus(currentAccount.getId(), Unpaid);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    public ResponseEntity<?> paymentOrder() {
        //thanh toan gio hang
        Account currentAccount = accountService.getCurrentAccount();
        Optional<Order> order = orderRepository.findOrderByAccountIdAndOrderStatus(currentAccount.getId(), Unpaid);
        Iterable<OrderDetails> orderDetails = orderDetailsService.findOrderDetailsByOrderId(order.get().getId());
        List<Product> products = productRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            for (OrderDetails orderDetailsOld : orderDetails) {
                if (Objects.equals(products.get(i).getProductID(), orderDetailsOld.getProduct().getProductID())) {
                    products.get(i).setStockQuantity(products.get(i).getStockQuantity() - orderDetailsOld.getQuantity());
                    productRepository.save(products.get(i));
                    break;
                }
            }
        }
        order.get().setOrderStatus(Paid);
        orderRepository.save(order.get());
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

//    public void removeOrderDetails( Long orderDetailsId){
//        OrderDetails orderDetails = null;
//        Account currentAccount = accountService.getCurrentAccount();
//        Optional<Order> order = orderRepository.findOrderByAccountIdAndOrderStatus(currentAccount.getId(), Unpaid);
//        for (OrderDetails ord: order.get().getOrderDetails()) {
//            if(ord.getId().equals(orderDetailsId)){
//                orderDetails = ord;
//                orderDetailsService.removeOrderDetailsByOrder_id(order.get().getId(), orderDetails);
//            }
//        }
//    }

}
