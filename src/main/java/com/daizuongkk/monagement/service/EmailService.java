package com.daizuongkk.monagement.service;

import com.daizuongkk.monagement.dto.EmailDTO;

public interface EmailService {
  void send(EmailDTO email);
}
