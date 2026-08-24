package com.daizuongkk.monagement.service;

import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.Identifier.IdentifierType;
import com.daizuongkk.monagement.entity.User;

/**
 * IdentifierService
 */
public interface IdentifierService {
  IdentifierType resolve(String value);

  String normalize(IdentifierType type, String input);

  Identifier create(User user, RegisterRequest registerRequest);
}
