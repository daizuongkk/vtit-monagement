package com.daizuongkk.monagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daizuongkk.monagement.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);

}
