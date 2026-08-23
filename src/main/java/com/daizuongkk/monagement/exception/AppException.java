package com.daizuongkk.monagement.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
  private final ErrorCode errorCode;

  private final Object[] args;

  public AppException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode;
    this.args = new Object[0];

  }

  public AppException(ErrorCode errorCode, Object... args) {
    super(errorCode.name());
    this.errorCode = errorCode;
    this.args = args;
  }

}
