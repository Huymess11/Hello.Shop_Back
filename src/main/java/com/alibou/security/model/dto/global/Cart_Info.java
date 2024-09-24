package com.alibou.security.model.dto.global;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart_Info {
    String code_Bill;
    String email;
    String name;
    String telephoneNumber;
    String address_to;
}
