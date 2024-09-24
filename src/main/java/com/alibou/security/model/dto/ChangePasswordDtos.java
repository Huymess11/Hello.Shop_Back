package com.alibou.security.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordDtos {
    private String oldPassword;
    private String newPassword;
    private String retypeNewPassword;
}
