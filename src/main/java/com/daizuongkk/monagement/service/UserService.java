package com.daizuongkk.monagement.service;

import com.daizuongkk.monagement.dto.request.RegisterRequest;
import com.daizuongkk.monagement.entity.User;

/**
 * UserService
 */
public interface UserService {

  User create(RegisterRequest registerRequest);

}
