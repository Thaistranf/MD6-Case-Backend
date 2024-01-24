package com.example.dailyshop.service.webservice.Order;

import com.example.dailyshop.model.account.Account;
import com.example.dailyshop.model.entity.Order;
import com.example.dailyshop.model.entity.OrderStatus;
import com.example.dailyshop.service.webservice.IGenerateService;
import org.springframework.data.domain.Sort;

import javax.naming.ldap.SortResponseControl;
import java.util.List;
import java.util.Optional;

public interface IOrderService extends IGenerateService<Order> {
    List<Order> findOrderByAccountId(Long accountId, Sort sort);

    List<Order> findOrderBySupplierId(Long supplierId,Sort sort);

    Optional<Order> findOrderByAccountIdAndSupplierIdAndOrderStatus(Long account_id, Long supplier_id, OrderStatus orderStatus);
}
