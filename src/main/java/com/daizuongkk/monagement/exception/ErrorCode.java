package com.daizuongkk.monagement.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ErrorCode
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {

	UNCATEGORIZED_EXCEPTION(
			4444,
			"Uncategorized error",
			HttpStatus.INTERNAL_SERVER_ERROR),

	INVALID_CREDENTIALS(
			1001,
			"Invalid username or password",
			HttpStatus.UNAUTHORIZED),

	UNAUTHENTICATED(
			1002,
			"Authentication required",
			HttpStatus.UNAUTHORIZED),

	INVALID_TOKEN(
			1003,
			"Invalid token",
			HttpStatus.UNAUTHORIZED),

	TOKEN_EXPIRED(
			1004,
			"Token expired",
			HttpStatus.UNAUTHORIZED),

	INVALID_REFRESH_TOKEN(
			1005,
			"Invalid refresh token",
			HttpStatus.UNAUTHORIZED),

	REFRESH_TOKEN_EXPIRED(
			1006,
			"Refresh token expired",
			HttpStatus.UNAUTHORIZED),

	ACCESS_DENIED(
			1007,
			"Access denied",
			HttpStatus.FORBIDDEN),

	USER_NOT_FOUND(3000, "User does not exists", HttpStatus.NOT_FOUND),

	USERNAME_EXISTED(3001, "Username already exists", HttpStatus.CONFLICT),

	;

	int code;

	String message;

	HttpStatus httpStatus;

}
