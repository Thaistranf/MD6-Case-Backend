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

    List<Product> findProductByAccountIdAndIsDeleted(Long id, boolean deleted);
    List<Product> findAllByCategoryId(Long id);
    List<Product> findProductByAccountIdAndIsDeleted(Long id, boolean deleted,Sort sort);

    Page<Product> findAllByIsDeleted(boolean deleted,Pageable pageable);

    @Query(value = "SELECT p FROM Product p join Category c ON p.category.id = c.id WHERE p.productName LIKE %:name% OR c.name LIKE %:category% OR p.price  >=:minPrice and p.price <=:maxPrice")
    List<Product> searchProducts(@Param("name") String name, @Param("category") String category, @Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);

//    @Query(value = "select ord.product, p.productName,sum(ord.quantity) as totalQuantity from CartDetails ord join Product p on p.productID = ord.product.productID join Cart o on o.id = ord.orderId where o.orderStatus = 'Paid' group by p.productName,ord.product order by totalQuantity desc limit 5")
//    List<Product> findTop5Products();

}
