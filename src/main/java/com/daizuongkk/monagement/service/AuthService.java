package com.daizuongkk.monagement.service;

import com.daizuongkk.monagement.dto.request.LoginRequest;
import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.dto.response.AuthenticationResponse;
import com.daizuongkk.monagement.dto.response.UserResponse;

public interface AuthService {

  AuthenticationResponse login(LoginRequest request);

  boolean logout();

  UserResponse register(RegisterRequest request);

}
