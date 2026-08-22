package com.daizuongkk.monagement.service.impl;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.request.LoginRequest;
import com.daizuongkk.monagement.dto.response.AuthenticationResponse;
import com.daizuongkk.monagement.dto.response.TokenResponse;
import com.daizuongkk.monagement.service.AuthService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

  AuthenticationManager authenticationManager;

  JwtService jwtService;

  @Override
  public AuthenticationResponse login(LoginRequest request) {

    var authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

    Authentication authentication = authenticationManager.authenticate(authenticationToken);

    TokenResponse token = jwtService.generateToken(authentication);

    String refreshToken = UUID.randomUUID().toString();

    return AuthenticationResponse
        .builder()
        .accessToken(token.getToken())
        .refreshToken(refreshToken)
        .expiresIn(token.getExpiresIn())
        .build();
  }

  @Override
  public boolean logout() {

    return true;
  }

}
