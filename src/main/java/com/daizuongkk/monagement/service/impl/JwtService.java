package com.daizuongkk.monagement.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.response.TokenResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

  @Value("${jwt.access-token.ttl}")
  private Long accessTokenTTL;

  private final JwtEncoder encoder;

  private final JwtDecoder decoder;

  public TokenResponse generateToken(Authentication authentication) {

    Instant now = Instant.now();

    JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256).build();

    Instant expirationTime = now.plus(accessTokenTTL, ChronoUnit.SECONDS);

    List<String> roles = authentication.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .toList();
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("dzkk")
        .issuedAt(now)
        .expiresAt(expirationTime)
        .subject(authentication.getName())
        .claim("role", roles)
        .id(UUID.randomUUID().toString())
        .build();

    JwtEncoderParameters encoderParameters = JwtEncoderParameters.from(jwsHeader,
        claims);

    Jwt jwt = encoder.encode(encoderParameters);

    return TokenResponse
        .builder()
        .token(jwt.getTokenValue())
        .expiresIn(jwt.getExpiresAt())
        .build();

  }

  public Jwt parse(String token) {
    return decoder.decode(token);
  }
}
