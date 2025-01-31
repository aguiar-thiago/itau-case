package br.com.itau.api.security.validator.impl;

import java.util.Set;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKey;
import br.com.itau.api.security.validator.ClaimValidator;

public class ClaimKeysValidator implements ClaimValidator {

	private static final Set<String> EXPECTED_KEYS = Set.of(
			ClaimKey.NAME.getKey(),
			ClaimKey.ROLE.getKey(),
			ClaimKey.SEED.getKey());

	@Override
	public boolean validate(DecodedJWT decodedJWT) {
		if (decodedJWT.getClaims().size() != 3) {
			return false;
		}

		Set<String> claimKeys = decodedJWT.getClaims().keySet();
		return claimKeys.equals(EXPECTED_KEYS);
	}

}
