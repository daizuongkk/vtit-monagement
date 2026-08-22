package com.daizuongkk.monagement.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.daizuongkk.monagement.service.impl.CustomUserDetailsService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {

  String[] PUBLIC_ENPOINTS = { "/auth/login", "/auth/refresh" };

  CustomUserDetailsService userDetailsService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) {
    http.csrf(csrf -> csrf.disable())
        // .cors(null)
        .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_ENPOINTS)
            .permitAll()
            .anyRequest()
            .authenticated())
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        .authenticationManager(authenticationManager());

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager() {

    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);

    authenticationProvider.setPasswordEncoder(passwordEncoder());

    return new ProviderManager(authenticationProvider);

  }

  @Bean
  public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder(12);

  }

}
