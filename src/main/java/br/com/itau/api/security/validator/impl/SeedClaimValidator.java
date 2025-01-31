package br.com.itau.api.security.validator.impl;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.utils.JWTUtils;
import br.com.itau.api.security.validator.ClaimValidator;

public class SeedClaimValidator implements ClaimValidator {

	@Override
	public boolean validate(DecodedJWT decodedJWT) {
		int seed = Integer.valueOf(decodedJWT.getClaim(ClaimKeyEnum.SEED.getKey()).asString());
		return JWTUtils.isPrime(seed);
	}

}
