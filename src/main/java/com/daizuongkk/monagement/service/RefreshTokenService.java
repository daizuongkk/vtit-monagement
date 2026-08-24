package com.daizuongkk.monagement.service;

import com.daizuongkk.monagement.entity.RefreshToken;
import com.daizuongkk.monagement.entity.User;

public interface RefreshTokenService {

  RefreshToken create(User user);

}