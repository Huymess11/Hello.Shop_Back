package com.alibou.security.controller;

import com.alibou.security.model.dto.global.SignInDtos;
import com.alibou.security.model.dto.AuthenticationDtos;
import com.alibou.security.service.AuthenticationService;
import com.alibou.security.model.dto.global.RegisterDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationDtos> register(
      @RequestBody RegisterDtos request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/login")
  public ResponseEntity<AuthenticationDtos> login(
      @RequestBody SignInDtos request
  ) {
    return ResponseEntity.ok(service.Login(request));
  }

}
