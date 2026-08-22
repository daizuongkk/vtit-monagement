package com.daizuongkk.monagement.exception;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse implements Serializable {
  private int code;
  private String error;
  private String message;

  @Builder.Default
  private Instant timestamp = Instant.now();
  private String path;
}