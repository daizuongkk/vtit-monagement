package com.daizuongkk.monagement.dto.response;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
	String accessToken;
	String refreshToken;
	Instant expiresIn;

	@Builder.Default
	String tokenType = "Bearer";
}
