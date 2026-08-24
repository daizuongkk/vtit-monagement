package com.daizuongkk.monagement.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.Identifier.IdentifierType;
import com.daizuongkk.monagement.repository.IdentifierRepository;
import com.daizuongkk.monagement.util.IdentifierResolver;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final IdentifierRepository identifierRepository;

  @Override
  public UserDetails loadUserByUsername(String rawIdentifier) throws UsernameNotFoundException {
    IdentifierType type = IdentifierResolver.resolve(rawIdentifier);
    String normalized = IdentifierResolver.normalize(type, rawIdentifier);

    Identifier identifier = identifierRepository
        .findByTypeAndValue(type.toString(), normalized)
        .orElseThrow(() -> new UsernameNotFoundException(rawIdentifier));

    return identifier.getUser();
  }

}
