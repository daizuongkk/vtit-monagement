package com.daizuongkk.monagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@RedisHash("refreshToken")
public class RefreshToken {

  @Id
  private String value;

  private String userId;

  @TimeToLive
  private Long expiresIn;

}
