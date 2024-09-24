package com.alibou.security.model.dto.global;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart_Product {
    Integer count;
    Integer id;
    String name;
    String imgURL;
    Float unitPrice;
    Float totalPrice;
}
