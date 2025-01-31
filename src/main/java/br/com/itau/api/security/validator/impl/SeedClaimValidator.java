package br.com.itau.api.security.validator.impl;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.utils.JWTUtils;
import br.com.itau.api.security.validator.ClaimValidator;

public class SeedClaimValidator implements ClaimValidator {

	@Override
	public boolean validate(DecodedJWT decodedJWT) throws JWTException {
		int seed = Integer.valueOf(decodedJWT.getClaim(ClaimKeyEnum.SEED.getKey()).asString());
		if (JWTUtils.isPrime(seed)) {
			return true;
		}
		
		throw new JWTException("Seed incorreto", 400);
	}

}
