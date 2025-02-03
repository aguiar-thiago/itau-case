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
	public void validate(DecodedJWT decodedJWT) throws JWTException {
		var seedValue = decodedJWT.getClaim(ClaimKeyEnum.SEED.getKey()).asString();

		isEmptyValue(ClaimKeyEnum.SEED, seedValue);

		int seed;
		try {
			seed = Integer.valueOf(seedValue);
		} catch (NumberFormatException e) {
			log.error("O valor do claim SEED não é um número válido: {}", seedValue);
			throw new JWTException("Claim SEED não é um número válido");
		}

		if (!JWTUtils.isPrime(seed)) {
			log.error("O claim SEED não é um número primo: {}", seed);
			throw new JWTException("O claim SEED não é um número primo!");
		}

	}

}
