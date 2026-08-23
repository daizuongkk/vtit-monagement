package com.daizuongkk.monagement.config;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import lombok.NonNull;

@Configuration
public class MessageConfig {

  @Bean
  public MessageSource messageSource() {

    ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();

    source.setBasenames("classpath:messages/common", "classpath:messages/validation", "classpath:messages/auth");
    source.setDefaultEncoding("UTF-8");
    source.setUseCodeAsDefaultMessage(true);

    return source;
  }

  @Bean
  public LocalValidatorFactoryBean validatorFactoryBean(@NonNull MessageSource messageSource) {

    LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();

    localValidatorFactoryBean.setValidationMessageSource(messageSource);

    return localValidatorFactoryBean;

  }

  @Bean
  public LocaleResolver localeResolver() {
    AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
    resolver.setDefaultLocale(Locale.forLanguageTag("vi"));
    resolver.setSupportedLocales(List.of(Locale.forLanguageTag("vi"), Locale.forLanguageTag("en")));
    return resolver;
  }

}
