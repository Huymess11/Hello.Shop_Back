package com.alibou.security.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CartSF {

    @Id
    @GeneratedValue
    private Integer id;
    private String codeBill;
    private String email;
    private String name;
    private String getTelephoneNumber;
    private String address_to;
    private Integer number_cartList;
    private Float totalPrice;

}
