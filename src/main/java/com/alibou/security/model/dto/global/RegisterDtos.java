package com.alibou.security.model.dto.global;

import com.alibou.security.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDtos {


  private String email;
  private String username;
  private String password;
  private String fullName;
}
