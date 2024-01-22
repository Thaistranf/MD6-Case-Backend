package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.*;
import com.example.dailyshop.repository.AccountRepository;
import com.example.dailyshop.repository.data.CartRepository;
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

@Service
public class ShoppingCartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CartDetailsService cartDetailsService;

    public Cart addProductToOrder(Long account_id, CartDetails orderDetail) {
        //thêm sản phẩm vào giỏ hàng.
        Optional<Cart> order = cartRepository.findCartByAccountId(account_id);
        Optional<Product> product = productRepository.findById(orderDetail.getProduct().getProductID());
        Cart cartNew;
        if (order.isPresent()) {
            cartNew = order.get();
        } else {
            cartNew = new Cart();
            cartNew.setOrderDate(LocalDateTime.now());
//            cartNew.setOrderStatus(Unpaid);
            cartNew.setCartDetails(new HashSet<>());
            Account account = accountRepository.findById(account_id).get();
            cartNew.setAccount(account);
        }
        boolean flag = false;
        for (CartDetails item : cartNew.getCartDetails()) {
            if (Objects.equals(item.getProduct().getProductID(), orderDetail.getProduct().getProductID())) {
                item.setQuantity(item.getQuantity() + orderDetail.getQuantity());
                flag = true;
                break;
            }
        }
        if (!flag) {
            orderDetail.setProduct(product.get());
            cartNew.getCartDetails().add(orderDetail);
        }
        BigDecimal totalAmount = getTotalAmount(cartNew);
        cartNew.setTotalAmount(totalAmount);
        int countOrderDetails = getQuantityOrder(cartNew);
        cartNew.setQuantity(countOrderDetails);
        return cartRepository.save(cartNew);


    }

    @NotNull
    private static BigDecimal getTotalAmount(Cart cartNew) {
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        for (CartDetails item : cartNew.getCartDetails()) {
            item.setPrice(item.getProduct().getPrice());
            totalAmount = totalAmount.add(BigDecimal.valueOf((long) item.getQuantity() * item.getPrice()));
        }
        return totalAmount;
    }

    public Integer getQuantityOrder(Cart cart) {
        int totalOrderDetails = 0;
        for (CartDetails count : cart.getCartDetails()) {
//            count.setQuantity(count.getQuantity());
            totalOrderDetails = totalOrderDetails + count.getQuantity();
        }
        return totalOrderDetails;
    }

    public ResponseEntity<Cart> getCart() {
        //xem giỏ hàng của khách hàng.
        Account currentAccount = accountService.getCurrentAccount();
        Optional<Cart> order = cartRepository.findCartByAccountId(currentAccount.getId());
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    public ResponseEntity<?> paymentOrder() {
        //thanh toan gio hang
        Account currentAccount = accountService.getCurrentAccount();
        Optional<Cart> order = cartRepository.findCartByAccountId(currentAccount.getId());
        Iterable<CartDetails> orderDetails = cartDetailsService.findCartDetailsByCartId(order.get().getId());
        List<Product> products = productRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            for (CartDetails cartDetailsOld : orderDetails) {
                if (Objects.equals(products.get(i).getProductID(), cartDetailsOld.getProduct().getProductID())) {
                    products.get(i).setStockQuantity(products.get(i).getStockQuantity() - cartDetailsOld.getQuantity());
                    productRepository.save(products.get(i));
                    break;
                }
            }
        }
        cartRepository.save(order.get());
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }


    public ResponseEntity<?> removeOrderDetail(Long orderDetailId) {
        Account account = accountService.getCurrentAccount();
        Optional<Cart> order = cartRepository.findCartByAccountId(account.getId());

        if (order.isPresent()) {
            if (!Objects.equals(order.get().getAccount().getId(), account.getId())) {
                return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
            }
            for (CartDetails details : order.get().getCartDetails()) {
                if (Objects.equals(details.getId(), orderDetailId)) {
                    order.get().getCartDetails().remove(details);
                    order.get().setQuantity(order.get().getCartDetails().size());
                    order.get().setTotalAmount(getTotalAmount(order.get()));
                    return new ResponseEntity<>(cartRepository.save(order.get()), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("Can't remove orderDetails", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> findOrderCustomerPaid() {
        Account account = accountService.getCurrentAccount();
        List<Cart> carts = cartRepository.findOrderByAccount(account);
        if (carts.isEmpty()) {
            return new ResponseEntity<>("Null Data", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(carts, HttpStatus.OK);
        }
    }
}
