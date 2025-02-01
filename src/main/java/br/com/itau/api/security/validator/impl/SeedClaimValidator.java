package br.com.itau.api.security.validator.impl;

import org.apache.commons.lang3.StringUtils;
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

	private static final int HTTP_BAD_REQUEST = 400;
    private static final String INVALID_SEED_ERROR_MESSAGE = "Seed incorreto";
	
	@Override
	public void validate(DecodedJWT decodedJWT) throws JWTException {
		String seedValue = decodedJWT.getClaim(ClaimKeyEnum.SEED.getKey()).asString();

		if (StringUtils.isEmpty(seedValue)) {
			log.error("O claim SEED está nulo ou vazio!");
			throw new JWTException(INVALID_SEED_ERROR_MESSAGE, HTTP_BAD_REQUEST);
		}
		
		int seed;
		try {
			seed = Integer.valueOf(seedValue);
		} catch (NumberFormatException e) {
			log.error("O valor do claim SEED não é um número válido: {}", seedValue);
			throw new JWTException(INVALID_SEED_ERROR_MESSAGE, HTTP_BAD_REQUEST);
		}

		if (!JWTUtils.isPrime(seed)) {
			log.error("O claim SEED não é um número primo: {}", seed);
			throw new JWTException(INVALID_SEED_ERROR_MESSAGE, HTTP_BAD_REQUEST);
		}
		
	}

}
