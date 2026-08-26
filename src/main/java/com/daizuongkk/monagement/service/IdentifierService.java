package com.daizuongkk.monagement.service;

import java.util.Optional;

import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.User;

/**
 * IdentifierService
 */
public interface IdentifierService {

  Identifier create(User user, RegisterRequest registerRequest);

  boolean isVerified(String identifier);

  boolean exists(String identifier);

  Optional<Identifier> getByValue(String identifier);
}
