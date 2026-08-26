package com.daizuongkk.monagement.service;

import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.Otp;

public interface OtpService {

  Otp create(Identifier identifier);

}
