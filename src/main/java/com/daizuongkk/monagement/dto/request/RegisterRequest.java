package com.daizuongkk.monagement.dto.request;

import com.daizuongkk.monagement.annotation.ValidIdentifier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequest {

  @NotBlank(message = "{identifier.required}")
  @ValidIdentifier(message = "{identifier.invalid}")
  private String identifier;

  @NotBlank(message = "{password.required}")
  @Size(min = 8, max = 64, message = "{password.length}")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$", message = "{password.invalid}")
  private String password;

}