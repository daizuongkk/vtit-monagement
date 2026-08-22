package com.daizuongkk.monagement.dto.response;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenResponse {

  private String token;

  private Instant expiresIn;

  private String tokenType;

}
