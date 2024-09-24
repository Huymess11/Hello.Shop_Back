package com.alibou.security.repositories;

import com.alibou.security.model.entities.CartDt;
import com.alibou.security.model.entities.CartSF;
import com.alibou.security.model.entities.Ob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartDtRepository extends JpaRepository<CartDt, Integer> {
//    Optional<CartDt> findByCart_id(Integer id);
    @Query("SELECT p FROM CartDt p " +
            "WHERE p.cart_id = :cart_id ")
    List<CartDt> findByCart_id(Integer cart_id);
}
