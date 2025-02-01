package br.com.itau.api.security.validator.impl;

import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.utils.JWTUtils;
import br.com.itau.api.security.validator.ClaimValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SeedClaimValidator implements ClaimValidator {

	@Override
	public boolean validate(DecodedJWT decodedJWT) throws JWTException {
		int seed = Integer.valueOf(decodedJWT.getClaim(ClaimKeyEnum.SEED.getKey()).asString());

		if (!JWTUtils.isPrime(seed)) {
			log.error("O claim SEED não é um número primo: {}", seed);
			throw new JWTException("Seed incorreto", 400);
		}
		
		return true;
	}

}
