package com.alibou.security.repositories;

import com.alibou.security.model.entities.CartSF;
import com.alibou.security.model.entities.Ob;
import com.alibou.security.model.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartSFRepository extends JpaRepository<CartSF, Integer>{
    Optional<CartSF> findByCodeBill(String codebill);
}
