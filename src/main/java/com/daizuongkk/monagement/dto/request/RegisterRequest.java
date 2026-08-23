package com.daizuongkk.monagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequest {

  @NotBlank(message = "{username.required}")
  @Size(min = 5, max = 30, message = "{username.length}")
  @Pattern(regexp = "^[\\w]+$", message = "{username.invalid}")
  private String username;

  @NotBlank(message = "email.required")
  @Email(message = "email.invalid")
  private String email;

  @NotBlank(message = "{password.required}")
  @Size(min = 8, max = 64, message = "{password.length}")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$", message = "{password.invalid}")
  private String password;

}