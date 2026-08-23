package com.daizuongkk.monagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@RedisHash
@Getter
@Setter
@Builder
public class RevokedToken {

  @Id
  private String jwtId;

  @TimeToLive
  private Long expiresIn;

}
