package com.alibou.security.service;

import com.alibou.security.model.dto.AuthenticationDtos;
import com.alibou.security.model.entities.Role;
import com.alibou.security.model.entities.Token;
import com.alibou.security.model.dto.global.SignInDtos;
import com.alibou.security.model.dto.global.RegisterDtos;
import com.alibou.security.repositories.TokenRepository;
import com.alibou.security.model.entities.TokenType;
import com.alibou.security.model.entities.User;
import com.alibou.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationDtos register(RegisterDtos request) {
    var user = User.builder()
        .username(request.getFullName())
            .fullName(request.getFullName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
            .address(null)
            .telephoneNumber(null)
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationDtos.builder()
        .jwt(jwtToken)
            .user(user)
        .build();
  }

  public AuthenticationDtos createAdmin(RegisterDtos request) {
    var user = User.builder()
            .username(request.getFullName())
            .fullName(request.getFullName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.ADMIN)
            .address("Long Bien, Ha Noi")
            .telephoneNumber("0988862237")
            .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationDtos.builder()
            .jwt(jwtToken)
            .user(user)
            .build();
  }

  public AuthenticationDtos Login(SignInDtos request) {
    final Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getIdentifier(),
            request.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    var user = repository.findByEmail(request.getIdentifier())
        .orElseThrow();

    var user1 = User.builder()
            .id(user.getId())
            .fullName(user.getFullName())
            .username(user.getUsername())
            .email(user.getEmail())
            .password(user.getPassword())
            .role(user.getRole())
            .address(user.getAddress())
            .telephoneNumber(user.getTelephoneNumber())
            .build();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationDtos.builder()
        .jwt(jwtToken)
            .user(user1)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
