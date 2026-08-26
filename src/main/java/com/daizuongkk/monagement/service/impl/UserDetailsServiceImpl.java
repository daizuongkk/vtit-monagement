package com.daizuongkk.monagement.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.User;
import com.daizuongkk.monagement.service.IdentifierService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final IdentifierService identifierService;

  @Override
  public UserDetails loadUserByUsername(String identifierValue) throws UsernameNotFoundException {

    Identifier identifier = identifierService.getByValue(identifierValue)
        .orElseThrow(() -> new UsernameNotFoundException(identifierValue));

    User user = identifier.getUser();

    user.setAuthenticationIdentifier(identifier);

    return user;
  }

}
