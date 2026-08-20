package com.daizuongkk.monagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

	@NotBlank(message = "{user.username.required}")
	@Size(min = 5, max = 30, message = "{user.username.length}")
	@Pattern(regexp = "^[\\w]+$", message = "{user.username.invalid}")
	String username;

	@NotBlank(message = "{user.password.required}")
	@Size(min = 8, max = 64, message = "{user.password.length}")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$", message = "{user.password.invalid}")
	String password;

}