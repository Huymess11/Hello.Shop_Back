package com.alibou.security.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CartDt {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer cart_id;
    private Integer count;
    private String name;
    private String imgURL;
    private Float unitPrice;
    private Float totalPrice;
}
