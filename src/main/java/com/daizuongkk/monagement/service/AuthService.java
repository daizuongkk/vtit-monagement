package com.daizuongkk.monagement.service;

import com.daizuongkk.monagement.dto.request.LoginRequest;
import com.daizuongkk.monagement.dto.response.AuthenticationResponse;

/**
 * AuthService
 */

public interface AuthService {

	AuthenticationResponse login(LoginRequest request);

}
