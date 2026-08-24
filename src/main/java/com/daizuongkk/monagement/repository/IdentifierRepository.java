package com.daizuongkk.monagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daizuongkk.monagement.entity.Identifier;

public interface IdentifierRepository extends JpaRepository<Identifier, Long> {

  boolean existsByValue(String value);

  Optional<Identifier> findByTypeAndValue(String type, String value);

  boolean existsByTypeAndValue(String identifierValue);

  boolean existsByUserId(String userId);

}
