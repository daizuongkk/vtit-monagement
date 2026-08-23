package com.daizuongkk.monagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.daizuongkk.monagement.entity.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

}
