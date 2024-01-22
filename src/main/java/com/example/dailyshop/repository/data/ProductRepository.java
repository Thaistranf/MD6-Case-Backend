package com.example.dailyshop.repository.data;

import com.example.dailyshop.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findProductByproductNameContaining(String name);

    List<Product> findProductByAccountIdAndIsDeleted(Long id, boolean deleted,Sort sort);

    List<Product> findAllByIsDeleted(boolean deleted,Sort sort);
    Page<Product> findAllByIsDeleted(boolean deleted,Pageable pageable);
    @Query(value = "SELECT p FROM Product p join Category c ON p.category.id = c.id WHERE p.productName LIKE %:name% OR c.name LIKE %:category% OR p.price  >=:minPrice and p.price <=:maxPrice")
    List<Product> searchProducts(@Param("name") String name,@Param("category") String category,@Param("minPrice") int minPrice,@Param("maxPrice") int maxPrice);
    List<Product> findAllByCategoryId(Long id);

    @Query(value = "select ord.product, p.productName,sum(ord.quantity) as totalQuantity from OrderDetails ord join Product p on p.productID = ord.product.productID join Order o on o.orderId = ord.orderId where o.orderStatus = 'Paid' group by p.productName,ord.product order by totalQuantity desc limit 5")
    List<Product> findTop5Products();
}
