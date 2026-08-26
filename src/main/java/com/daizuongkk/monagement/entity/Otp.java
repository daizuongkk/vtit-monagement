package com.daizuongkk.monagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import com.daizuongkk.monagement.entity.Identifier.IdentifierType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash(value = "otp")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Otp {

  @Id
  private String id;

  private String code;

  private String identifier;
  private IdentifierType identifierType;

  @Builder.Default
  private int attempts = 5;

  @TimeToLive
  private Long expiresIn;
}
