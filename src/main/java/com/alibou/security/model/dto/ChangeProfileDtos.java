package com.alibou.security.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeProfileDtos {
    private String address;
    private String fullName;
    private String telephoneNumber;
}
