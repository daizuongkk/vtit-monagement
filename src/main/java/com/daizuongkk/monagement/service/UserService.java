package com.daizuongkk.monagement.service;

import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.dto.response.UserResponse;

/**
 * UserService
 */
public interface UserService {

  UserResponse create(RegisterRequest registerRequest);

}
