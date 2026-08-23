package com.daizuongkk.monagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.daizuongkk.monagement.entity.RevokedToken;

public interface RevokedTokenRepository extends CrudRepository<RevokedToken, String> {

}
