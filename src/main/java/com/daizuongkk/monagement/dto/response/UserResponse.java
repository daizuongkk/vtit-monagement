package com.daizuongkk.monagement.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {
  private Long id;
  private ProfileResponse profile;

}
