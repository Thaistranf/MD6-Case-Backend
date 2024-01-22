package com.example.dailyshop.repository.data;

import com.example.dailyshop.model.entity.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails,Long> {

    @Query("SELECT od FROM CartDetails od WHERE od.cartId =:id")
    List<CartDetails> findByOrderId(@Param("id") Long id);

    Optional<CartDetails> findCartDetailsByCartIdAndId(Long cartId,Long productId);

    Integer countCartDetailsByCartId(Long cartId);


}
