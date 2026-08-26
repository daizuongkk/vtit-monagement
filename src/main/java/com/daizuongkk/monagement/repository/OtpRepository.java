package com.daizuongkk.monagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.daizuongkk.monagement.entity.Identifier.IdentifierType;
import com.daizuongkk.monagement.entity.Otp;

public interface OtpRepository extends CrudRepository<Otp, String> {

  Optional<Otp> findByIdentifierAndIdentifierTypeAndOtpCode(
      String identifier,
      IdentifierType identifierType,
      String otpCode);

}
