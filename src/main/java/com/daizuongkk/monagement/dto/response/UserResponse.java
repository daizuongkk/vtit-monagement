package com.daizuongkk.monagement.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {

  private String id;
  private String username;
  private ProfileResponse profileResponse;

}
