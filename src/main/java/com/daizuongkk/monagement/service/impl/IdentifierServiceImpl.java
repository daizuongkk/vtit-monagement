package com.daizuongkk.monagement.service.impl;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.Identifier.IdentifierType;
import com.daizuongkk.monagement.entity.User;
import com.daizuongkk.monagement.exception.AppException;
import com.daizuongkk.monagement.exception.ErrorCode;
import com.daizuongkk.monagement.repository.IdentifierRepository;
import com.daizuongkk.monagement.service.IdentifierService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdentifierServiceImpl implements IdentifierService {
  private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
  private final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

  private final IdentifierRepository identifierRepository;

  @Override
  public Optional<Identifier> getByValue(String identifier) {
    return identifierRepository.findByTypeAndValue(resolve(identifier), identifier);
  }

  @Override
  public boolean isVerified(String identifier) {

    IdentifierType identifierType = resolve(identifier);

    return identifierRepository.existsByTypeAndValueAndVerifiedTrue(identifierType,
        normalize(identifier));

  }

  private IdentifierType resolve(String value) {
    if (EMAIL_PATTERN.matcher(value).matches()) {
      return IdentifierType.EMAIL;
    }
    try {
      var number = phoneUtil.parse(value, "VN");
      if (phoneUtil.isValidNumber(number)) {
        return IdentifierType.PHONE;
      }
      throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);
    } catch (NumberParseException e) {
      throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);
    }
  }

  @Override
  public boolean exists(String identifier) {
    IdentifierType identifierType = this.resolve(identifier);
    return identifierRepository.existsByTypeAndValue(identifierType, normalize(identifier));
  }

  @Override
  public Identifier create(User user, RegisterRequest registerRequest) {

    boolean isFirst = !identifierRepository.existsByUserId(user.getId());

    IdentifierType identifierType = resolve(registerRequest.getIdentifier());
    String identifierValue = normalize(registerRequest.getIdentifier());

    Identifier identifier = Identifier.builder()
        .user(user)
        .type(identifierType)
        .value(identifierValue)
        .isPrimary(isFirst)
        .build();

    return identifierRepository.save(identifier);
  }

  private String normalize(String identifier) {

    IdentifierType type = resolve(identifier);

    return switch (type) {
      case EMAIL -> identifier.trim().toLowerCase();
      case PHONE -> {
        try {
          var number = phoneUtil.parse(identifier, "VN");
          yield phoneUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
          throw new AppException(ErrorCode.INVALID_PHONE_NUMBER);
        }
      }
      default -> throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
    };
  }

}
