package com.daizuongkk.monagement.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.response.TokenResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtService {

  @NonFinal
  @Value("${jwt.access-token.ttl}")
  Long accessTokenTTL;

  JwtEncoder encoder;

  public TokenResponse generateToken(Authentication authentication) {

    Instant now = Instant.now();

    JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS512).build();

    Instant expirationTime = now.plus(accessTokenTTL, ChronoUnit.SECONDS);

    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(now)
        .expiresAt(expirationTime)
        .subject(authentication.getName())
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
}
