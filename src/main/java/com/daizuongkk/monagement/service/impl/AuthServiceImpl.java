package com.daizuongkk.monagement.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.request.LoginRequest;
import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.dto.response.AuthenticationResponse;
import com.daizuongkk.monagement.dto.response.TokenResponse;
import com.daizuongkk.monagement.dto.response.UserResponse;
import com.daizuongkk.monagement.entity.RevokedToken;
import com.daizuongkk.monagement.repository.RevokedTokenRepository;
import com.daizuongkk.monagement.service.AuthService;
import com.daizuongkk.monagement.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  private final UserService userService;

  private final RevokedTokenRepository revokedTokenRepository;

  @Override
  public AuthenticationResponse login(LoginRequest request) {

    var authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

    Authentication authentication = authenticationManager.authenticate(authenticationToken);

    TokenResponse token = jwtService.generate(authentication);

    String refreshToken = UUID.randomUUID().toString();

    return AuthenticationResponse
        .builder()
        .accessToken(token.getToken())
        .refreshToken(refreshToken)
        .expiresIn(token.getExpiresIn())
        .build();
  }

  @Override
  @Transactional
  public UserResponse register(RegisterRequest request) {

    // TODO send welcome email using message queue
    return userService.create(request);
  }

  @Override
  public void logout(String token) {
    Jwt jwt = jwtService.parse(token);

    Instant issuedAt = jwt.getIssuedAt();
    Instant expiresAt = jwt.getExpiresAt();
    Instant now = Instant.now();
    if (now.isBefore(issuedAt))
      return;

    revokedTokenRepository.save(RevokedToken.builder()
        .jwtId(jwt.getId())
        .expiresIn(Duration.between(issuedAt, expiresAt).toSeconds())
        .build());
  }

}
