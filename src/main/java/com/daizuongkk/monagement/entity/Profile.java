package com.daizuongkk.monagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table(name = "profiles")
@Entity
@Getter
@Setter
@Builder
public class Profile extends BaseEntity {

  private String firstName;

  private String lastName;

  private String address;

  private LocalDate dob;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

}
