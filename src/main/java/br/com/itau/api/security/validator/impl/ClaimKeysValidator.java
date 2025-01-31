package br.com.itau.api.security.validator.impl;

import java.util.Set;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.validator.ClaimValidator;

public class ClaimKeysValidator implements ClaimValidator {

	private static final Set<String> EXPECTED_KEYS = Set.of(
			ClaimKeyEnum.NAME.getKey(),
			ClaimKeyEnum.ROLE.getKey(),
			ClaimKeyEnum.SEED.getKey());

	@Override
	public boolean validate(DecodedJWT decodedJWT) throws JWTException {
		if (decodedJWT.getClaims().size() != 3) {
			throw new JWTException("Claims diferente do esperado", 400);
		}

		Set<String> claimKeys = decodedJWT.getClaims().keySet();
		if (!claimKeys.equals(EXPECTED_KEYS) ) {
			throw new JWTException("claim nao mapeado", 400);
		}
		return true;
	}

}
