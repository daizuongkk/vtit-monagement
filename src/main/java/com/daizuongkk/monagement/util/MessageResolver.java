package com.daizuongkk.monagement.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageResolver {

  private final MessageSource messageSource;

  public String resolve(String key) {
    return resolve(key, null);
  }

  public String resolve(String key, Object[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(key, args, key, locale);
  }
}