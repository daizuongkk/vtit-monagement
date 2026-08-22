package com.daizuongkk.monagement.dto.response;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenResponse {

	String token;
	Instant expiresIn;
	String tokenType;

}
