package com.daizuongkk.monagement.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import com.daizuongkk.monagement.repository.RevokedTokenRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RevokedTokenValidator implements OAuth2TokenValidator<Jwt> {
  private final RevokedTokenRepository revokedTokenRepository;

  @Override
  public OAuth2TokenValidatorResult validate(Jwt token) {

    String jwtId = token.getId();

    if (revokedTokenRepository.existsById(jwtId)) {

      OAuth2Error error = new OAuth2Error("token_revoked", "Token has been revoked", null);
      return OAuth2TokenValidatorResult.failure(error);
    }
    return OAuth2TokenValidatorResult.success();
  }

}
