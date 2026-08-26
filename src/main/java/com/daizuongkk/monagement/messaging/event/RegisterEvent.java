package com.daizuongkk.monagement.messaging.event;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEvent implements Serializable {
  private String eventId;
  private String email;
  private String username;
  private String otpCode;
}
