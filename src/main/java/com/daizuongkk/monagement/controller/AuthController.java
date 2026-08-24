package com.daizuongkk.monagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daizuongkk.monagement.dto.request.LoginRequest;
import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.dto.response.ApiResponse;
import com.daizuongkk.monagement.dto.response.AuthenticationResponse;
import com.daizuongkk.monagement.dto.response.RegisterResponse;
import com.daizuongkk.monagement.dto.response.UserResponse;
import com.daizuongkk.monagement.service.AuthService;
import com.daizuongkk.monagement.util.MessageResolver;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  private final MessageResolver messageResolver;

  @PostMapping("/login")
  ResponseEntity<ApiResponse<AuthenticationResponse>> login(@Valid @RequestBody LoginRequest request) {

    var response = authService.login(request);

    return ResponseEntity.ok(ApiResponse.<AuthenticationResponse>builder()
        .data(response)
        .message(messageResolver.resolve("auth.login.success"))
        .build());
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {

    var response = authService.register(request);
    return ResponseEntity
        .ok(ApiResponse.<RegisterResponse>builder()
            .data(response)
            .message(messageResolver.resolve("auth.register.success"))
            .build());
  }

  @PostMapping("/logout")
  public ResponseEntity<ApiResponse<Void>> logout(@AuthenticationPrincipal Jwt jwt) {

    authService.logout(jwt.getTokenValue());

    return ResponseEntity.ok(ApiResponse.<Void>builder()
        .message(messageResolver.resolve("auth.logout.success"))
        .build());
  }

}
