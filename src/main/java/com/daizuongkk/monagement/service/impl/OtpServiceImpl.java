package com.daizuongkk.monagement.service.impl;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.Otp;
import com.daizuongkk.monagement.exception.AppException;
import com.daizuongkk.monagement.exception.ErrorCode;
import com.daizuongkk.monagement.repository.OtpRepository;
import com.daizuongkk.monagement.service.OtpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

  private static final Random RANDOM = new SecureRandom();

  private final OtpRepository otpRepository;

  @Override
  public Otp create(Identifier identifier) {

    String otpCode = String.format("%06d", RANDOM.nextInt(1000000));

    Otp otp = Otp.builder()
        .code(otpCode)
        .expiresIn(300L)
        .identifier(identifier.getValue())
        .identifierType(identifier.getType())
        .build();

    return otpRepository.save(otp);
  }

  public boolean verify(Identifier identifier, String otpCode) {

    Otp otp = otpRepository
        .findByIdentifierAndIdentifierTypeAndOtpCode(identifier.getValue(), identifier.getType(), otpCode)
        .orElseThrow(() -> new AppException(ErrorCode.INVALID_OTP));

    return true;
  }

}
