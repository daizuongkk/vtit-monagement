package com.daizuongkk.monagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

	@NotBlank(message = "{user.username.required}")
	String username;

	@NotBlank(message = "{user.password.required}")
	String password;

}
