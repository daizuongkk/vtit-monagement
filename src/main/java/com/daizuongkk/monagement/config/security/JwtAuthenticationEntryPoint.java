package com.daizuongkk.monagement.config.security;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.daizuongkk.monagement.exception.ErrorCode;
import com.daizuongkk.monagement.exception.ErrorResponse;
import com.daizuongkk.monagement.util.MessageResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final MessageResolver messageResolver;
  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

    ErrorResponse errorResponse = ErrorResponse.builder()
        .code(errorCode.getCode())
        .message(messageResolver.resolve(errorCode.getMessage()))
        .error(errorCode.getHttpStatus().getReasonPhrase())
        .path(request.getRequestURI())
        .build();

    response.getWriter()
        .write(objectMapper.writeValueAsString(errorResponse));
  }

}
