package com.daizuongkk.monagement.config.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {
	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
}