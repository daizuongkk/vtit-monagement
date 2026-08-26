package com.daizuongkk.monagement.service.impl;

import java.time.Duration;
import java.time.Instant;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.ObjectUtils;

import com.daizuongkk.monagement.config.RabbitMQConfig;
import com.daizuongkk.monagement.dto.request.LoginRequest;
import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.dto.response.AuthenticationResponse;
import com.daizuongkk.monagement.dto.response.RegisterResponse;
import com.daizuongkk.monagement.dto.response.TokenResponse;
import com.daizuongkk.monagement.entity.Identifier;
import com.daizuongkk.monagement.entity.RefreshToken;
import com.daizuongkk.monagement.entity.RevokedToken;
import com.daizuongkk.monagement.entity.User;
import com.daizuongkk.monagement.exception.AppException;
import com.daizuongkk.monagement.exception.ErrorCode;
import com.daizuongkk.monagement.messaging.event.RegisterEvent;
import com.daizuongkk.monagement.repository.RevokedTokenRepository;
import com.daizuongkk.monagement.service.AuthService;
import com.daizuongkk.monagement.service.IdentifierService;
import com.daizuongkk.monagement.service.OtpService;
import com.daizuongkk.monagement.service.RefreshTokenService;
import com.daizuongkk.monagement.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UserService userService;
  private final RevokedTokenRepository revokedTokenRepository;
  private final IdentifierService identifierService;
  private final RefreshTokenService refreshTokenService;
  private final RabbitTemplate rabbitTemplate;
  private final OtpService otpService;

  @Override
  public AuthenticationResponse login(LoginRequest request) {

    var authenticationToken = new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getPassword());
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    User user = (User) authentication.getPrincipal();

    if (ObjectUtils.isEmpty(user))
      throw new AppException(ErrorCode.UNAUTHENTICATED);

    if (!user.getAuthenticationIdentifier().isVerified()) {
      throw new AppException(ErrorCode.IDENTIFIER_NOT_VERIFIED);
    }

    TokenResponse token = jwtService.generateToken(authentication);

    RefreshToken refreshToken = refreshTokenService.create(user);

    return AuthenticationResponse
        .builder()
        .accessToken(token.getToken())
        .refreshToken(refreshToken.getValue())
        .expiresIn(token.getExpiresIn())
        .build();
  }

  @Override
  @Transactional
  public RegisterResponse register(RegisterRequest request) {

    String identifierValue = request.getIdentifier();

    if (identifierService.exists(identifierValue))
      throw new AppException(ErrorCode.IDENTIFIER_EXISTED);

    User user = userService.create(request);

    Identifier identifier = identifierService.create(user, request);

    RegisterEvent event = RegisterEvent.builder()
        .email(identifierValue)
        .otpCode(otpService.create(identifier).getCode())
        .build();

    TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
      @Override
      public void afterCommit() {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.USER_REGISTER_SUCCESS_QUEUE,
            event);
      }
    });
    return RegisterResponse.builder()
        .userId(user.getId())
        .identifier(identifier.getValue())
        .identifierType(identifier.getType())
        .createdAt(identifier.getCreatedAt())
        .build();
  }

  @Override
  public void logout(String token) {
    Jwt jwt = jwtService.parse(token);

    Instant issuedAt = jwt.getIssuedAt();
    Instant expiresAt = jwt.getExpiresAt();
    Instant now = Instant.now();
    if (now.isBefore(issuedAt))
      return;

    revokedTokenRepository.save(RevokedToken.builder()
        .jwtId(jwt.getId())
        .expiresIn(Duration.between(issuedAt, expiresAt).toSeconds())
        .build());
  }

}
