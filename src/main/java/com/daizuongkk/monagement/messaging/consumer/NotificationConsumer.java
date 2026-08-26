package com.daizuongkk.monagement.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.daizuongkk.monagement.config.RabbitMQConfig;
import com.daizuongkk.monagement.dto.EmailDTO;
import com.daizuongkk.monagement.messaging.event.RegisterEvent;
import com.daizuongkk.monagement.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

  private final EmailService emailService;

  @RabbitListener(queues = RabbitMQConfig.USER_REGISTER_SUCCESS_QUEUE)
  public void handleUserRegister(RegisterEvent event) {
    log.info("Received user registration event for email: {}, eventId: {}", event.getEmail(), event.getEventId());

    String idempotencyKey = "processed:event:" + event.getEventId();

    EmailDTO email = EmailDTO.builder()

        .recipient(event.getEmail())
        .subject("Email Verification")
        .content(event.getOtpCode())
        .build();

    emailService.send(email);

  }
}
