package com.alibou.security.model.dto;

import com.alibou.security.model.dto.global.Cart_Info;
import com.alibou.security.model.dto.global.Cart_Product;
import com.alibou.security.model.entities.User;
import com.codingerror.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDtos {
    @JsonProperty("info")
    private Cart_Info info;

    @JsonProperty("cart_list")
    private List<Cart_Product> cart_list;
}
