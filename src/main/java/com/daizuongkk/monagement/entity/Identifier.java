package com.daizuongkk.monagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "identifiers")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Identifier extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private IdentifierType type;

  private String value;

  private boolean primary;

  @Builder.Default
  private boolean verified = false;

  @ManyToOne
  private User user;

  public enum IdentifierType {
    EMAIL, PHONE, GITHUB, GOOGLE
  }
}
