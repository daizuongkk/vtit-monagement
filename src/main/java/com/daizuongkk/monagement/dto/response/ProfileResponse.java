package com.daizuongkk.monagement.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfileResponse {

  private Long id;
  private Long userId;
  private String firstName;
  private String lastName;
  private String address;
  private LocalDate dob;

}
