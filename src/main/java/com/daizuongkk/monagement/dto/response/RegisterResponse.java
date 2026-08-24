package com.daizuongkk.monagement.dto.response;

import java.time.Instant;

import com.daizuongkk.monagement.entity.Identifier.IdentifierType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterResponse {

  private String userId;
  private String identifier;
  private IdentifierType identifierType;
  private Instant createdAt;

}
