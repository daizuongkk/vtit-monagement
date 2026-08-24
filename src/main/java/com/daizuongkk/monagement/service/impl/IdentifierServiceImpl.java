package com.daizuongkk.monagement.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.service.IdentifierService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdentifierServiceImpl implements IdentifierService {
  @Override
  public Identifier resolve(String value) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'resolve'");
  }

  @Override
  public void ensureNotExists(Identifier identifier) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ensureNotExists'");
  }

  @Override
  public Identifier create(UUID userId, Identifier identifier) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public Identifier getByIdentifier(Identifier identifier) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getByIdentifier'");
  }

}
