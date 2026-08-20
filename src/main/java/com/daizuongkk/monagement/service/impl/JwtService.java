package com.daizuongkk.monagement.service.impl;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.daizuongkk.monagement.config.security.RsaKeyProperties;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtService {

	@Value("${jwt.access-token.ttl}")
	Long accessTokenTTL;

	RsaKeyProperties rsaKeys;

	public String generateToken(Authentication authentication) throws JOSEException {

		JWSHeader header = new JWSHeader(JWSAlgorithm.RS256);

		Instant now = Instant.now();
		Date issueTime = Date.from(now);
		Date expirationTime = Date.from(now.plusSeconds(accessTokenTTL));

		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.issuer("sefl")
				.issueTime(issueTime)
				.expirationTime(expirationTime)
				.subject(authentication.getName())

				.build();

		SignedJWT signedJWT = new SignedJWT(header, claimsSet);

		RSASSASigner signer = new RSASSASigner(rsaKeys.getPrivateKey());

		signedJWT.sign(signer);

		return signedJWT.serialize();

	}
}
