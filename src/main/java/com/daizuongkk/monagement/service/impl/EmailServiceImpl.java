package com.daizuongkk.monagement.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.EmailDTO;
import com.daizuongkk.monagement.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender sender;

  @Override
  public void send(EmailDTO email) {

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(email.getRecipient());
    mailMessage.setSubject(email.getSubject());
    mailMessage.setText(email.getContent());
    sender.send(mailMessage);

    log.info("send email successful: {}", email.getRecipient());

  }

}
