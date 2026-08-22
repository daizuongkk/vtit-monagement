package com.daizuongkk.monagement.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterResponse {
  private Long id;
  private String username;
}
