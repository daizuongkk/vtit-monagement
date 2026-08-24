package com.daizuongkk.monagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.Identifier.IdentifierType;

public interface IdentifierRepository extends JpaRepository<Identifier, Long> {

  boolean existsByValue(String value);

  Optional<Identifier> findByTypeAndValue(IdentifierType type, String value);

  boolean existsByTypeAndValue(IdentifierType type, String identifierValue);

  boolean existsByUserId(String userId);

}
