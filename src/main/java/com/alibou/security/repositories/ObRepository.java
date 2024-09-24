package com.alibou.security.repositories;

import com.alibou.security.model.entities.Ob;
import com.alibou.security.model.entities.Token;
import com.alibou.security.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ObRepository extends JpaRepository<Ob, Integer> {

    @Query("SELECT p FROM Ob p WHERE  p.brand = :brands ")
    List<Ob> find_brand(@Param("brands") String brands);

    @Query("SELECT p FROM Ob p " +
            "WHERE p.price >= :priceGte " +
            "AND p.price <= :priceLte " +
            "AND p.brand = :brand " +
            "AND (LOWER(p.name) " +"LIKE LOWER(CONCAT('%', :search, '%'))) ")
    List<Ob> search_all(@Param("brand") String brand,
                         @Param("priceGte") Integer priceGte,
                         @Param("priceLte") Integer priceLte,
                         @Param("search") String search);

    @Query("SELECT p FROM Ob p " +
            "WHERE p.price >= :priceGte " +
            "AND p.price <= :priceLte ")
    List<Ob> find_range_price(@Param("priceGte") Integer priceGte,
                         @Param("priceLte") Integer priceLte);


    @Query("SELECT p FROM Ob p " +
            "WHERE p.price >= :priceGte " +
            "AND p.price <= :priceLte "+
            "AND (LOWER(p.name) " +"LIKE LOWER(CONCAT('%', :search, '%'))) ")
    List<Ob> find_search_range(@Param("priceGte") Integer priceGte,
                              @Param("priceLte") Integer priceLte,
                               @Param("search") String search);

    @Query("SELECT p FROM Ob p " +
            "WHERE p.price >= :priceGte " +
            "AND p.price <= :priceLte "+
            "AND p.brand = :brand")
    List<Ob> find_brand_range(@Param("brand") String brand,
                                @Param("priceGte") Integer priceGte,
                               @Param("priceLte") Integer priceLte);
    @Query("SELECT p FROM Ob p " +
            "WHERE  (LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))) ")
    List<Ob> find_search(@Param("search") String search);

    @Query("SELECT p FROM Ob p " +
            "WHERE  (LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))) "+
    "AND p.brand = :brands")
    List<Ob> find_brand_search(@Param("brand") String brand,
            @Param("search") String search);


    Optional<Ob> findByName(String name);
}
