package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductService extends IGenerateService<Product> {
    List<Product> findProductByproductNameContaining(String name);


    List<Product> findAllByCategoryId(Long id);

    List<Product> findProductByAccountIdAndIsDeleted(Long id, boolean deleted, Sort sort);

    Page<Product> findAllByIsDeleted(boolean deleted, Pageable pageable);


    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name% ORDER BY p.price desc")
    List<Product> findByProductNameContaining(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name% ORDER BY p.price")
    List<Product> findProductsByProductNameContaining(@Param("name") String name);

    @Query(value = "select ord.product, p.productName,sum(ord.quantity) as totalQuantity from OrderDetails ord join Product p on p.productID = ord.product.productID join Order o on o.orderId = ord.orderId where o.orderStatus = 'Paid' group by p.productName,ord.product order by totalQuantity desc limit 5")
    List<Product> findTop5Products();
}
