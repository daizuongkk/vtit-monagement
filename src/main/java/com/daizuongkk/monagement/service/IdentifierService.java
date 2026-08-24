package com.daizuongkk.monagement.service;

import java.util.UUID;

import com.daizuongkk.monagement.entity.Identifier;

/**
 * IdentifierService
 */
public interface IdentifierService {
  Identifier resolve(String value);

  void ensureNotExists(Identifier identifier);

  Identifier create(UUID userId, Identifier identifier);

  Identifier getByIdentifier(Identifier identifier);
}
