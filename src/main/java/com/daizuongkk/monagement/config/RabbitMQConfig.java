package com.daizuongkk.monagement.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultJacksonJavaTypeMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String USER_REGISTER_SUCCESS_QUEUE = "user.register.success";

  @Bean
  public Queue userRegisterSuccessQueue() {
    return QueueBuilder
        .durable(USER_REGISTER_SUCCESS_QUEUE)
        .build();
  }

  @Bean
  public MessageConverter messageConverter() {
    return new JacksonJsonMessageConverter();
  }

  @Bean
  public ClassMapper classMapper() {
    DefaultJacksonJavaTypeMapper mapper = new DefaultJacksonJavaTypeMapper();

    mapper.setTrustedPackages(
        "com.daizuongkk.monagement.messaging.event");

    return mapper;
  }
}