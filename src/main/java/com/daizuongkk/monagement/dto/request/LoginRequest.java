package com.daizuongkk.monagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

  @NotBlank(message = "{username.required}")
  private String identifier;

  @NotBlank(message = "{password.required}")
  private String password;

}
