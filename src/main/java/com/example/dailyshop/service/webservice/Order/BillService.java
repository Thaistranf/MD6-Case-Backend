package com.example.dailyshop.service.webservice.Order;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.account.Supplier;
import com.example.dailyshop.model.entity.*;
import com.example.dailyshop.repository.data.OrderRepository;
import com.example.dailyshop.service.AccountService;
import com.example.dailyshop.service.SupplierService;
import com.example.dailyshop.service.webservice.CartService;
import com.example.dailyshop.service.webservice.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BillService {

    @Autowired
    private CartService cartService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailsService orderDetailsService;


    public List<Order> paymentBillCustomer() {
        List<Order> orders = new ArrayList<>();
        Account account = accountService.getCurrentAccount();
        Optional<Cart> cart = cartService.findCartByAccountId(account.getId());
        if (cart.isEmpty() || cart.get().getCartDetails().isEmpty()) {
            return orders;
        }
        Iterable<Supplier> suppliers = supplierService.findByCartId(cart.get().getId());
        Set<CartDetails> cartDetails = cart.get().getCartDetails();
        for (Supplier supplier : suppliers) {
            Order order = getOrder(supplier, account, cartDetails);
            orders.add(order);
            orderRepository.save(order);
            cartService.delete(cart.get().getId());
        }
        return orders;
    }

    @NotNull
    private static Order getOrder(Supplier supplier, Account account, Set<CartDetails> cartDetails) {
        Order order = new Order();
        order.setSupplier(supplier);
        order.setAccount(account);
        order.setOrderStatus(OrderStatus.Unpaid);
        order.setOrderDate(LocalDateTime.now());
        Set<OrderDetails> orderDetails = getOrderDetails(supplier, cartDetails);
        order.setOrderDetails(orderDetails);
        order.setQuantity(orderDetails.size());
        order.setTotalMoney(getTotalAmount(orderDetails));
        return order;
    }

    @NotNull
    private static Set<OrderDetails> getOrderDetails(Supplier supplier, Set<CartDetails> cartDetails) {
        Set<OrderDetails> orderDetails = new HashSet<>();
        for (CartDetails item : cartDetails) {
            if (Objects.equals(item.getProduct().getAccount().getId(), supplier.getAccount().getId())) {
                OrderDetails newItem = new OrderDetails();
                newItem.setQuantity(item.getQuantity());
                newItem.setProduct(item.getProduct());
                newItem.setPrice(item.getProduct().getPrice());
                orderDetails.add(newItem);
            }
        }
        return orderDetails;
    }

    @NotNull
    private static BigDecimal getTotalAmount(Set<OrderDetails> orderDetails) {
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        for (OrderDetails item : orderDetails) {
            item.setPrice(item.getPrice());
            totalAmount = totalAmount.add(BigDecimal.valueOf((long) item.getQuantity() * item.getPrice()));
        }
        return totalAmount;
    }

    public List<Order> createBillCustomer() {
        //Thanh toán đơn hàng cho khách hàng.
        return paymentBillCustomer();
    }

    public List<Order> getOrder() {
        //xem đơn hàng của khách hàng.
        Account currentAccount = accountService.getCurrentAccount();
        List<Order> order = orderService.findOrderByAccountId(currentAccount.getId());
        if (order.isEmpty()) {
            return null;
        } else {
            return order;
        }
    }

    public List<Order> getOrderBySupplier() {
        Account account = accountService.getCurrentAccount();
        Optional<Supplier> supplier = supplierService.findByAccountId(account.getId());
        return orderRepository.findOrderBySupplierId(supplier.get().getId());
    }

    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderService.findById(orderId).get();
        order.setOrderStatus(orderStatus);
        List<OrderDetails> orderDetails = orderDetailsService.findOrderDetailsByOrderId(order.getOrderId());
        List<Product> products = productService.findAll();
        for (int i = 0; i < products.size(); i++) {
            for (OrderDetails ordOld : orderDetails) {
                if (Objects.equals(products.get(i).getProductID(), ordOld.getProduct().getProductID())) {
                    products.get(i).setStockQuantity(products.get(i).getStockQuantity() - order.getQuantity());
                    productService.save(products.get(i));
                    break;
                }
            }
        }
        orderService.save(order);
        return order;
    }
}
