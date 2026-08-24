package com.daizuongkk.monagement.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ErrorCode
 */

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  UNCATEGORIZED_EXCEPTION(4444, "common.internal.error", HttpStatus.INTERNAL_SERVER_ERROR),

  INVALID_CREDENTIALS(1001, "auth.credentials.invalid", HttpStatus.UNAUTHORIZED),
  UNAUTHENTICATED(1002, "auth.unauthenticated", HttpStatus.UNAUTHORIZED),
  INVALID_TOKEN(1003, "auth.token.invalid", HttpStatus.UNAUTHORIZED),
  TOKEN_EXPIRED(1004, "auth.token.expired", HttpStatus.UNAUTHORIZED),
  INVALID_REFRESH_TOKEN(1005, "auth.refreshtoken.invalid", HttpStatus.UNAUTHORIZED),
  REFRESH_TOKEN_EXPIRED(1006, "auth.refreshtoken.expired", HttpStatus.UNAUTHORIZED),
  ACCESS_DENIED(1007, "auth.access.denied", HttpStatus.FORBIDDEN),

  USER_NOT_FOUND(3000, "user.notfound", HttpStatus.NOT_FOUND),
  USERNAME_EXISTED(3001, "auth.username.duplicate", HttpStatus.CONFLICT),

  IDENTIFIER_EXISTED(3002, "auth.identifier.duplicate", HttpStatus.CONFLICT),

  INVALID_PHONE_NUMBER(3002, "phone.number.invalid", HttpStatus.BAD_REQUEST)

  ;

  private final int code;

  private final String message;

  private final HttpStatus httpStatus;

}
