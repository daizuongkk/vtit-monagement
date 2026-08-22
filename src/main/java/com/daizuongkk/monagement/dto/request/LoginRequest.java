package com.daizuongkk.monagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

  @NotBlank(message = "{user.username.required}")
  private String username;

  @NotBlank(message = "{user.password.required}")
  private String password;

}
